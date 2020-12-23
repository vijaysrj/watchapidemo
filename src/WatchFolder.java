import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchFolder {

	
	public static void main(String a[]) {
		
	watchFolder();
	}
	public static void watchFolder() {

		try {

			System.out.println("Watching directory for changes");

			// STEP1: Create a watch service
			WatchService watchService = FileSystems.getDefault().newWatchService();

			// STEP2: Get the path of the directory which you want to monitor.
			Path directory = Path.of("G:\\projects\\fullstackdeveloperblog\\watchapi");

			// STEP3: Register the directory with the watch service
			WatchKey watchKey = directory.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
					StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

			// STEP4: Poll for events
			while (true) {
				for (WatchEvent<?> event : watchKey.pollEvents()) {

					// STEP5: Get file name from even context
					WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;

					Path fileName = pathEvent.context();

					// STEP6: Check type of event.
					WatchEvent.Kind<?> kind = event.kind();

					// STEP7: Perform necessary action with the event
					if (kind == StandardWatchEventKinds.ENTRY_CREATE) {

						System.out.println("A new file is created : " + fileName);
					}

					if (kind == StandardWatchEventKinds.ENTRY_DELETE) {

						System.out.println("A file has been deleted: " + fileName);
					}
					if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {

						System.out.println("A file has been modified: " + fileName);
					}

				}

				// STEP8: Reset the watch key everytime for continuing to use it for further event polling
				boolean valid = watchKey.reset();
				if (!valid) {
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
