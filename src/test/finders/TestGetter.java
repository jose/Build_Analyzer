package test.finders;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;

import util.TaskHelper;

public class TestGetter {
	
	private Target target;
	private List<RuntimeConfigurable> batchTests, includes, excludes;
	
	public TestGetter(Target target) {
		this.target = target;
		batchTests = new ArrayList<RuntimeConfigurable>();
		includes = new ArrayList<RuntimeConfigurable>();
		excludes = new ArrayList<RuntimeConfigurable>();
		this.getPatterns();
	}
	
	public String getIncludesPattern() {
		String ret = "";
		for(RuntimeConfigurable include:includes) {
			ret = ret+include.getAttributeMap().get("name")+"\n";
		}
		return ret;
	}
	
	public String getExcludesPattern() {
		String ret = "";
		for(RuntimeConfigurable exclude:excludes) {
			ret = ret+exclude.getAttributeMap().get("name")+"\n";
		}
		return ret;
	}
	
	private void getPatterns() {
		List<Task> tasks = TaskHelper.getTasks("junit", target);
		for(Task task:tasks) {
			Enumeration<RuntimeConfigurable> junitSubTasks = task.getRuntimeConfigurableWrapper().getChildren();
			this.getSubTask("batchtest", junitSubTasks, batchTests);
			for(RuntimeConfigurable batch : batchTests) {
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
