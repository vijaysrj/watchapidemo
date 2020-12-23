import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WatchExecutor {

	public static void main(String a[]) {

		System.out.println("Starting a background thread for watching folders");
		ExecutorService executor = Executors.newCachedThreadPool();

		executor.submit(new WatchCallable());

		System.out.println("After submitting Callable for watching folder");
	}
}
