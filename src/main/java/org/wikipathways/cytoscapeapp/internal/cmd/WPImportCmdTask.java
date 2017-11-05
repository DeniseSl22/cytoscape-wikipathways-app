package org.wikipathways.cytoscapeapp.internal.cmd;

import java.io.Reader;

import org.cytoscape.work.AbstractTask;
import org.cytoscape.work.FinishStatus;
import org.cytoscape.work.ObservableTask;
import org.cytoscape.work.TaskIterator;
import org.cytoscape.work.TaskManager;
import org.cytoscape.work.TaskMonitor;
import org.cytoscape.work.TaskObserver;
import org.cytoscape.work.Tunable;
import org.wikipathways.cytoscapeapp.impl.GpmlConversionMethod;
import org.wikipathways.cytoscapeapp.impl.GpmlReaderFactory;
import org.wikipathways.cytoscapeapp.impl.ResultTask;
import org.wikipathways.cytoscapeapp.impl.WPClient;
import org.wikipathways.cytoscapeapp.impl.WPPathway;

public class WPImportCmdTask extends AbstractTask {
//  static final Pattern WP_ID_REGEX = Pattern.compile("WP\\d+");

  @Tunable
  public String id;

  final WPClient client;
  final GpmlReaderFactory factory;
  final GpmlConversionMethod method;
  TaskManager<?,?> taskMgr;

  public WPImportCmdTask(
      final WPClient client,
      final GpmlReaderFactory factory,
      final GpmlConversionMethod method,
      TaskManager<?,?> inTaskMgr
    ) {
    this.client = client;
    this.factory = factory;
    this.method = method;
    taskMgr = inTaskMgr;
  }

  public void run(TaskMonitor monitor) {
    if (id == null || id.length() == 0) {
      throw new IllegalArgumentException("id must be specified");
    }
    System.out.println("We know the id:  " + id);
    final ResultTask<WPPathway> infoTask = client.pathwayInfoTask(id);
    final TaskIterator taskIterator = new TaskIterator(infoTask);
      taskIterator.append(new AbstractTask() {
        public void run(TaskMonitor monitor) {
        	WPPathway pathway = infoTask.get();
        	if (pathway == null)
        	{
        		System.err.println("Shit!");
        		return;
        }
          super.insertTasksAfterCurrentTask(new AbstractTask() {
              public void run(TaskMonitor monitor) {
          		System.err.println("loading!");
              	factory.getWPManager().loadPathway(method, pathway, taskMgr);
              }
           });
     taskMgr.execute(taskIterator, new TaskObserver() {
        public void taskFinished(ObservableTask t) {}
        public void allFinished(FinishStatus status) {  System.err.println("done!");}
 });


  } });
  }
}

