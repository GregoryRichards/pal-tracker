package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {


    Map<Object, Object> envMap;

    public EnvController(@Value("${PORT:NOT SET}") String port,
                         @Value("${MEMORY_LIMIT:NOT SET}") String memLim,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String cfIdx,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String cfAddr )
    {
        envMap = new HashMap<Object,Object>();
        envMap.put( "PORT", port );
        envMap.put( "MEMORY_LIMIT", memLim );
        envMap.put( "CF_INSTANCE_INDEX", cfIdx );
        envMap.put( "CF_INSTANCE_ADDR", cfAddr );
    }


    @GetMapping("/env")
    public Map getEnv()
    {
        return envMap;
    }


}
