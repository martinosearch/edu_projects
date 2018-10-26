package function;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

import connection.MartConnection;

public class FichierEditor {

	private static String backupDir;

	public static void writeSauvegarde() {
		Fichier bat = new Fichier(Constance.getTempDir() + "db_dump.bat");
		bat.create();

		backupDir = "D:\\BackUp\\" + MartConnection.getDataBase();

		System.out.println("Sauvegarde: " + backupDir);

		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("@echo off");
		cmds.add("set PGPASSWORD=martin90");
		cmds.add("set PGuser=postgres");
		cmds.add("set PGinstance=databaseinstance");
		cmds.add("set PGdump=C:\\Program Files\\PostgreSQL\\9.4\\bin\\pg_dump");
		// cmds.add("set PGdump=C:\\Program Files
		// (x86)\\PostgreSQL\\9.4\\bin\\pg_dump");
		cmds.add("set BackupDir=" + backupDir);
		cmds.add("set BackupName=martscoData");
		cmds.add("set DateTime=( %DATE:~-10,2%_%DATE:~-7,2%_%DATE:~-4% %TIME:~0,2%_%TIME:~3,2%_%TIME:~6,2% )");
		cmds.add("echo %DateTime%");
		cmds.add("mkDir %BackupDir%");
		cmds.add(
				"\"%PGdump%\" -h localhost -p 5432 -f \"%BackupDir%\\%BackupName%%DateTime%.backup\" -Fc -Z9 -v -U %PGuser% \""
						+ MartConnection.getDataBase() + "\"");
		cmds.add(
				"for /F \"tokens=* skip=500\" %%A in ('dir /b /a-d /tc /o-d \"%BackupDir%\\%BackupName%*.backup\"') do del \"%BackupDir%\\%%~A\"");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bat));
			String command = "";
			for (String str : cmds) {
				command += str + "\r\n";
			}

			writer.write(command);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeMiseAJour() {
		Fichier bat = new Fichier(Constance.getTempDir() + "maj.bat");
		bat.create();

		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("@echo off");
		cmds.add("\"" + Constance.getMyFolder() + "MiseAjourDBMartSCO.jar\"");
		cmds.add("exit");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bat));
			String command = "";
			for (String str : cmds) {
				command += str + "\r\n";
			}

			writer.write(command);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeRestore() {
		Fichier bat = new Fichier(Constance.getTempDir() + "db_restore.bat");
		bat.create();

		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("@echo off");
		cmds.add("\"" + Constance.getMyFolder() + "restoreDB.jar\"");
		cmds.add("exit");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bat));
			String command = "";
			for (String str : cmds) {
				command += str + "\r\n";
			}

			writer.write(command);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeRestart() {
		Fichier bat = new Fichier(Constance.getTempDir() + "rst.bat");
		bat.create();

		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("@echo off");
		cmds.add("\"" + Constance.getMyFolder() + Constance.getJarName() + "\"");
		cmds.add("exit");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bat));
			String command = "";
			for (String str : cmds) {
				command += str + "\r\n";
			}

			writer.write(command);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeFreeMemory() {
		Fichier bat = new Fichier(Constance.getTempDir() + "freeMemory.bat");
		bat.create();

		ArrayList<String> cmds = new ArrayList<String>();
		cmds.add("@echo off");
		cmds.add("del /q " + Constance.getTempDir() + "temp_images");
		cmds.add("rd " + Constance.getTempDir() + "temp_images");
		cmds.add("del /q " + Constance.getTempDir() + "db_dump.bat");
		cmds.add("del /q " + Constance.getTempDir() + "db_restore.bat");
		cmds.add("del /q " + Constance.getTempDir() + "del.bat");
		cmds.add("exit");

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(bat));
			String command = "";
			for (String str : cmds) {
				command += str + "\r\n";
			}

			writer.write(command);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
