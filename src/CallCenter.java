import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CallCenter {

	private final BlockingQueue<Call> _calls = new ArrayBlockingQueue<Call>(10);

	private final List<Thread> _threads;
	private final int _numberOfAgents;

	public CallCenter(int numberOfAgents) {
		this._threads = new ArrayList(numberOfAgents);
		this._numberOfAgents = numberOfAgents;
	}

	public void open() {
		assert this._threads.isEmpty() : "Already open";

		System.out.println("Call center opening");

		for (int i = 0; i < _numberOfAgents; i++) {
			Thread thread = new Thread(new CallCenterAgent(i, _calls));

			thread.start();
			this._threads.add(thread);
		}
		System.out.println("Call center is open");
	}

	public void accept(Call call) {
		assert !_threads.isEmpty() : "Not open";
		_calls.add(call);  
		System.out.println(call + " queued");
	}

	public void close() {
		assert !_threads.isEmpty() : "Already closed";
		System.out.println("Call center closing");
		for (int i = 0; i < _numberOfAgents; ++i) {
			accept(CallCenterAgent.GO_HOME);
		}
		
		for (Thread thread : _threads) {
			waitForTermination(thread);
		}

		_threads.clear();
		System.out.println("Call center closed");
	}

	private void waitForTermination(Thread thread) {
		try {
			thread.join();
		} catch (InterruptedException e) {
		}
	}

}
