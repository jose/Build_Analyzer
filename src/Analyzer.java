import java.io.File;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;

import target.finders.TargetGetter;
import test.finders.TestGetter;


public class Analyzer {
	
	private Project project;
	private TargetGetter targetGetter;
	private TestGetter testGetter;
	
	public Analyzer(File buildFile) {
		
		//Load in build file
		project = new Project();
		project.init();
		ProjectHelper helper = new ProjectHelper();
		helper.configureProject(project, buildFile);
		
		//Initialize Target getter
		if(project.getDefaultTarget() != null)
			targetGetter = new TargetGetter(project.topoSort(project.getDefaultTarget(), project.getTargets()));
		else
			targetGetter = new TargetGetter(project.topoSort("", project.getTargets()));
		
		//Initialize Test getter
		testGetter = new TestGetter(targetGetter.getJunitTarget());
	}
	
	public Target getCompileTarget() {
		return targetGetter.getCompileTarget();
	}
	
	public Target getCompileTestTarget() {
		return targetGetter.getCompileTestTarget();
	}
	
	public String getIncludes() {
		return testGetter.getIncludesPattern();
	}
	
	public String getExcludes() {
		return testGetter.getExcludesPattern();
	}
	
	public List<String> getTests() {
		return null;
	}
	
	
	
}
