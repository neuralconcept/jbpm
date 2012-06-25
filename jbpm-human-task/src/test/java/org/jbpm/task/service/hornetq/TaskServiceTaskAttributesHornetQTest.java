package org.jbpm.task.service.hornetq;

import org.drools.SystemEventListenerFactory;
import org.jbpm.task.service.TaskClient;
import org.jbpm.task.service.TaskServiceTaskAttributesBaseTest;

public class TaskServiceTaskAttributesHornetQTest extends TaskServiceTaskAttributesBaseTest {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		server = new HornetQTaskServer(taskService, 5446);
		logger.debug("Waiting for the HornetQTask Server to come up");
        try {
            startTaskServerThread(server, false);
        } catch (Exception e) {
            startTaskServerThread(server, true);
        }

		client = new TaskClient(new HornetQTaskClientConnector("client 1",
								new HornetQTaskClientHandler(SystemEventListenerFactory.getSystemEventListener())));
		client.connect("127.0.0.1", 5446);
	}

}