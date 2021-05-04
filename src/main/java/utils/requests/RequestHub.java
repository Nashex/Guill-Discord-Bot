package utils.requests;

import java.util.HashMap;
import java.util.Map;

public class RequestHub {

    public static Map<String, String> headers = new HashMap<>(
            Map.of("Connection", "keep-alive",
                    "Cache Control", "max-age=0",
                    "Upgrade Insecure Requests", "1",
                    "Sec Fetch Site", "none",
                    "Sec Fetch Mode", "navigate",
                    "Sec Fetch User", "1",
                    "Sec Fetch Dest", "document",
                    "Accept Encoding", "gzip, deflate, br",
                    "Accept Language", "en-US,en;q=0.9",
                    "Cookie", "n=1")
    );

}
