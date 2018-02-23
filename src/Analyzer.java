import java.io.File;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;

import target.finders.TargetGetter;


public class Analyzer {
	private Project project;
	private Target compileTarget, testCompileTarget;
	private Vector sortedTargets;
	private TargetGetter targetGetter;
	
	public Analyzer(File buildFile) {
		
		//Load in build file
		project = new Project();
		project.init();
		ProjectHelper helper = new ProjectHelper();
		helper.configureProject(project, buildFile);
		
		//Invoke Target getter
		targetGetter = new TargetGetter(project.topoSort("", project.getTargets()));
		
//		if(project.getDefaultTarget() != null)
//			sortedTargets = project.topoSort(project.getDefaultTarget(), project.getTargets());
//		else
//			sortedTargets = project.topoSort("", project.getTargets());
//		
//		
//			
//		//Print out all targets in execution order
//		Enumeration vEnum = sortedTargets.elements();
//
//	    while(vEnum.hasMoreElements())
//	    		System.out.println(vEnum.nextElement() + "\n");
		
		
	}
	
	public Target getCompileTarget() {
		return targetGetter.getCompileTarget();
	}
	
	public Target getCompileTestTarget() {
		System.out.println(targetGetter.getCompileTestTarget().toString());
		return targetGetter.getCompileTestTarget();
	}
	
	
	
	
	
}
