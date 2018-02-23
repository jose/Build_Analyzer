
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import util.WildCardResolver;

public class Driver {

	public static void main(String[] args) {
		String pathToProject = "";
		String pathToOutput = "";
		File dir, buildFile;
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please input a path to the project: ");
		pathToProject = scanner.nextLine();
		System.out.println("Please input a path to write the result: ");
		pathToOutput = scanner.nextLine();
		
		dir = new File(pathToProject);
		if(!dir.isDirectory()) {
			System.out.println("Not a directory");
		} else {
			String[] includes = {"*build**.xml","*Build**.xml"};
			String[] excludes = {};
			String buildFiles[] = WildCardResolver.resolveWildCard(includes, excludes, dir.toString());
			if(buildFiles.length == 1) {
				buildFile = new File(dir.getPath() + Paths.get("/") + buildFiles[0]);
			}
			else if(buildFiles.length == 0) {
				System.out.println("No build file found, please manually input your build file name: ");
				int index = pathToProject.lastIndexOf(Paths.get("/").toString());
				buildFile = new File(Paths.get(pathToProject.substring(0,index+1)) + Paths.get("/").toString() + scanner.nextLine());
			}
			else{
				System.out.println("More than 1 *build.xml files found, please manually input your build file name:");
				int index = pathToProject.lastIndexOf(Paths.get("/").toString());
				buildFile = new File(Paths.get(pathToProject) + Paths.get("/").toString() + scanner.nextLine());
			}
			System.out.println("Build file: "+buildFile);
			Analyzer analyzer = new Analyzer(buildFile);
			analyzer.getCompileTestTarget();
		}
		
	}

}
