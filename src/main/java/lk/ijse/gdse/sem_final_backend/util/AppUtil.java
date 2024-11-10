package lk.ijse.gdse.sem_final_backend.util;

import java.util.UUID;

public class AppUtil {
    public static String createStaffID() {
        return "ST-" + UUID.randomUUID();
    }
}
