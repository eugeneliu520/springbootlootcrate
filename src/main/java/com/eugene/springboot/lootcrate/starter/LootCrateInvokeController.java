package com.eugene.springboot.lootcrate.starter;

import com.eugene.springboot.lootcrate.registry.ServiceInvoke;
import org.springframework.web.bind.annotation.*;

/**
 * @author eugeneliu
 * @ClassName LootCrateInvokeController
 * @Description TODO
 * @Date 2024/4/19 11:45 AM
 **/
@LootCrateControllerMarker
@RestController
public class LootCrateInvokeController {

    @PostMapping("/rootCrate/invoke")
    public String invoke(@RequestBody InvokeParam invokeParam) {
        return ServiceInvoke.invoke(invokeParam.getBizId(),invokeParam.getServiceName(),invokeParam.getServiceMethod(),invokeParam.getParams());
    }
}
