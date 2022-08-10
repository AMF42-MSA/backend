import java.util.UUID;

public class UUIDCreate {

    public static void main(String[] args) {
    	for (int i = 0 ; i < 10; i++) {
        	UUID uuid = UUID.randomUUID();
        	

        	System.out.println("uuid:" + uuid.version() + ":"+ uuid);
    	}
	}
}
