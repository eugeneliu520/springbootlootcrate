package com.eugene.springboot.lootcrate.starter;

import com.eugene.springboot.lootcrate.deploy.DeployManager;
import com.eugene.springboot.lootcrate.deploy.DeployRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author eugeneliu
 * @ClassName DeployController
 * @Description TODO
 * @Date 2024/4/19 11:27 AM
 **/
@LootCrateControllerMarker
@RestController
public class DeployController {

    @Value("${lootcrate.admin.downloadUrl}")
    private String adminUrlDownLoad;

    @Value("${lootcrate.localJarStorage}")
    private  String localJarStorage;

    @Resource
    DeployManager deployManager;

    @GetMapping("/rootCrate/showMsg")
    public String showMsg(@RequestParam("msg") String msg) {
        return msg+"："+adminUrlDownLoad+"："+localJarStorage;
    }

    @GetMapping("/rootCrate/deploy")
    public String deploy(@RequestParam("type") String type,
                         @RequestParam("typeVersion") String typeVersion,
                         @RequestParam("deployStrategyType") String deployStrategyType,
                         @RequestParam("activeEnvironment") String activeEnvironment
                         ) {
        String fileUrl = null;
        try {
            fileUrl = download(fileName(type,typeVersion),adminUrlDownLoad,localJarStorage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> options = new HashMap<>();
        options.put("Constants.ACTIVE_ENVIRONMENT",activeEnvironment);
        DeployRequest deployRequest = new DeployRequest(type,typeVersion,deployStrategyType,"deployVersion",fileUrl,"",options);
        try {
            deployManager.deploy(deployRequest);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        return "Hello World!";
    }

    public static String download(String fileName, String url,String localJarStorage) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url+fileName);

        Path targetDirectory = Paths.get(localJarStorage);
        if (Files.notExists(targetDirectory)) {
            Files.createDirectories(targetDirectory);
        }
        return targetDirectory+"/"+fileName+".jar";
//        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
//            InputStream inputStream = response.getEntity().getContent();
//            File downloadFile = new File(targetDirectory+"/"+fileName);
//            FileOutputStream outputStream = new FileOutputStream(downloadFile);
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//            return targetDirectory+"/"+fileName;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            httpClient.close();
//        }
//        return null;
    }

    public static String fileName(String type,String typeVersion){
        return type+"-"+typeVersion;
    }
}
