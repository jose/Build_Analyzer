package testgetter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;

import util.TaskHelper;

public class TestGetter {
	
	private Target target;
	private List<RuntimeConfigurable> batchTests, filesets, includes, excludes;
	
	public TestGetter(Target target) {
		this.target = target;
		batchTests = new ArrayList<RuntimeConfigurable>();
		filesets = new ArrayList<RuntimeConfigurable>();
		includes = new ArrayList<RuntimeConfigurable>();
		excludes = new ArrayList<RuntimeConfigurable>();
		this.getPatterns();
//		System.out.println("includes: "+includes.size());
//		System.out.println("excludes: "+excludes.size());
	}
	
	public String getIncludesPattern() {
		String ret = "";
		
		if(this.filesets != null) {
			for(RuntimeConfigurable fileset:filesets) {
				if(fileset.getAttributeMap().get("includes") != null)
					ret = ret+fileset.getAttributeMap().get("includes")+"\n";
			}
		}
		
		if(this.includes != null) {
			for(RuntimeConfigurable include:includes) {
				if(include.getAttributeMap().get("name") != null)
					ret = ret+include.getAttributeMap().get("name")+"\n";
			}
		}
		System.out.println("includes: "+ret);
		return ret;
	}
	
	public String getExcludesPattern() {
		String ret = "";
		
		if(this.filesets != null) {
			for(RuntimeConfigurable fileset:filesets) {
				if(fileset.getAttributeMap().get("excludes") != null)
					ret = ret+fileset.getAttributeMap().get("excludes")+"\n";
			}
		}
		
		if(this.excludes != null) {
			for(RuntimeConfigurable exclude:excludes) {
				if(exclude.getAttributeMap().get("name") != null)
					ret = ret+exclude.getAttributeMap().get("name")+"\n";
			}
		}
		System.out.println("excludes: "+ret);
		return ret;
	}
	
	public String getTestDir() {
		String dir = "";
		if(filesets != null) {
			for(RuntimeConfigurable fileset:filesets) {
				if(fileset.getAttributeMap().get("dir") != null)
					dir=fileset.getAttributeMap().get("dir")+"";
			}
		}
		return dir;
	}
	
	private void getPatterns() {
		List<Task> tasks = TaskHelper.getTasks("junit", target);
		for(Task task:tasks) {
			Enumeration<RuntimeConfigurable> junitSubTasks = task.getRuntimeConfigurableWrapper().getChildren();
			this.getSubTask("batchtest", junitSubTasks, batchTests);
			for(RuntimeConfigurable batch : batchTests) {
				this.getSubTask("fileset", batch.getChildren(), filesets);
				this.getSubTask("include", batch.getChildren(), includes);
				this.getSubTask("exclude", batch.getChildren(), excludes);
			}
		}
	}
	
	private void getSubTask(String taskName, Enumeration<RuntimeConfigurable> subTasks, List<RuntimeConfigurable> list) {
		RuntimeConfigurable temp = null;
		while(subTasks.hasMoreElements()) {
			RuntimeConfigurable next = subTasks.nextElement();
			if(next!=null) {
				temp = next;
				if(temp.getElementTag().equalsIgnoreCase(taskName)) {
					list.add(temp);
				}
			}
		}
		if(temp != null)
			getSubTask(taskName, temp.getChildren(), list);
	}
	
}
