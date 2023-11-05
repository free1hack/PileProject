package com.calcu1;
//https://programmerbay.com/java-program-to-implement-stack/
//https://www.codejava.net/java-se/networking/java-socket-server-examples-tcp-ip
//echo client : https://docs.oracle.com/javase/tutorial/networking/sockets/readingWriting.html
//http://gallium.inria.fr/~maranget/X/421/poly/piles.html


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import com.calcu1.common.TypeOperation;

public class CalcRPL {

	private final int size = 100;
	private final int port = 1234;

	Socket socket;

	BufferedReader inputUser;//
	PrintStream outputUser;//
	PrintWriter outputLog;//
	TypeOperation typeOperation = null;
	PileRPL pile;

	Boolean logMode, logRecording = false, loop, remoteUser = false, replayMode = false;
	String fileLog = "log.txt";
	int nobj = 0;


	public CalcRPL(String[] args) throws Exception {
		socket = null;
		pile = new PileRPL(size);
		initStreams(args);
		mainLoop();

	}

	void initStreams(String[] args) throws Exception {
		if (args.length == 0) {
			setLocalUser();
		} else if ((args.length == 1)) {
			switch (args[0]) {
			case "0":
				setLocalUser();
				break;
			case "1":
				setRemoteUser();
				// dans la fct setRemote, il faut voir remoteUser = true;
				break;
			default:
				System.out.println("parameter error\n");
				System.exit(1);
			}
		} else {
			System.out.println("Expected at most one argument");
			System.exit(1);
		}
		System.out.println("length of array is: \n " + args.length);

	}

	public void getResult(String userArithmeticExpression) throws Exception {
		UserExpressionParsing userExpressionParsing = new UserExpressionParsing(userArithmeticExpression, pile, typeOperation, outputUser);
		userExpressionParsing.buildPile();

	}
	private boolean readUserMode() throws IOException {
		outputUser.println("chose 1: for record mode, 2: replay mode, 0: normal mode, other character: the session will close");
		String option = inputUser.readLine();
		if(option.equals("1")) {
			logRecording = true;
			setLogUser();
		} else if(option.equals("2")) {
			replayMode = true;
			setReplay();
		} else if(!option.equals("0")){
			return false;
		}
		return true;
	}
	public TypeOperation readTypeOperationFromUser() throws IOException {
		outputUser.println("chose Number: To user real number, Complex: to user complex number, otherwise the session will close");
		String option = inputUser.readLine();
		if(option.equals("Number")) {
			return TypeOperation.Number;
		} else if(option.equals("Complex")) {
			return TypeOperation.Complex;
		} else {
			return null;
		}
	}
	private void sendOptionsToUser() throws IOException {
		outputUser.println("You can start running your operation. To clear the pile write 'CLEAR', to quite you have to write 'END' or typing an empty line to quit");
	}

	private void mainLoop() throws Exception {
		String option = null;
		typeOperation = readTypeOperationFromUser();

		if(typeOperation != null && readUserMode()) {
			sendOptionsToUser();
			option = "START";
		} else {
			option = null;
		}
		while ((option != null && !option.isEmpty())) {
			option = readOperationUser();
		}
		quit();
	}

	// remote
	void setRemoteUser() throws IOException {

		try (ServerSocket socketServer = new ServerSocket(port)) {
			socket = socketServer.accept();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			inputUser = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			outputUser = new PrintStream(socket.getOutputStream(), true);
			outputUser.println("Hello User. Welcome to our server");
		} catch (Exception e) {
			System.err.println("Erreur : " + e);
			e.printStackTrace();
			if(inputUser != null ) {
				inputUser.close();
			}
			if(outputUser != null ) {
				outputUser.close();
			}
			System.exit(1);
		}
	}

	// local
	void setLocalUser() throws IOException {
		inputUser = new BufferedReader(new InputStreamReader(System.in));
		outputUser = System.out;
	}

	void setReplay() throws FileNotFoundException {
		if (Files.exists(FileSystems.getDefault().getPath(fileLog))) {
			inputUser = new BufferedReader(new FileReader(fileLog));
		} else {
			System.out.println("No log file !!!");
			System.exit(1);
		}

	}

	void setLogUser() throws FileNotFoundException {
		try {
			//1 - Lire le chemin du ficher s'il existe ou pas
			Path path = FileSystems.getDefault().getPath(fileLog);
			//2 - Supprimer le ficher s'il existe
			Files.deleteIfExists(path);
			//3 - Créer un nouveau ficher
			outputLog = new PrintWriter(new File(fileLog));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void quit() throws IOException {
		outputUser.println("fin de session");
		outputUser.close();
		inputUser.close();
		if (remoteUser == true) {
			socket.close();
		}
		if (logRecording) {
			outputLog.close();
		}
	}

	private String readOperationUser() {
		String line = null;
		try {
			line = inputUser.readLine();
			if (line == null || line.isEmpty() || line.equals("END")) {
				return null;
			}
			if (logRecording) {
				outputLog.println(line);
			}
			if(line.equals("CLEAR")) {
				this.getPile().clearPile();
				return line;
			}
			getResult(line);
		} catch (Exception e) {
			outputUser.println(e.getMessage());
			return null;
		}

		return line;
	}

	public PileRPL getPile() {
		return this.pile;
	}
}