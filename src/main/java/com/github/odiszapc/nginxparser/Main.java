package com.github.odiszapc.nginxparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {

    private static NgxConfig  conf;

    private static String NGINX_PATH="/Users/heidsoft/work/mw-openresty-config/prod/etc/openresty";

    private static String NGINX_UPSTREAM_PATH =  NGINX_PATH+"/upstreams";

    private static String NGINX_ENABLE_SITE_PATH = NGINX_PATH +"/site.enable";

    private static String[] confArray = new String[]{
            "activities.conf",
            "api.conf",
            "content-api.conf",
            "content-serving.conf",
            "druid.conf",
            "game.conf",
            "internal-system.conf",
            "meta-service.conf",
            "mgnt.conf",
            "misc.conf",
            "mlink.conf",
            "nginx-vhosts.conf",
            "preview-api.conf",
            "stats.conf",
            "system.conf",
            "video.conf",
            "xiaoan.conf"
    };

    private static List<String> confList = Arrays.asList(confArray);

    public static void main(String[] args) throws IOException {
//        conf = NgxConfig.read(NGINX_ENABLE_SITE_PATH+"/"+"mgnt.conf");
//        listPort(conf);

        for(String cfg : confList){
            //System.out.println("config name:"+cfg);
            conf = NgxConfig.read(NGINX_UPSTREAM_PATH+"/"+cfg);
            listUpstreamPort(conf);
        }


    }

    public static void listUpstreamPort(NgxConfig  conf){
        List<NgxEntry> servers = conf.findAll(NgxConfig.BLOCK,  "upstream");
        for (NgxEntry entry : servers) {
            NgxBlock block = (NgxBlock) entry;
            NgxParam ngxParam = block.findParam("server");
            System.out.println(ngxParam.getValue());
        }
    }

    public static void listPort(NgxConfig  conf){
        List<NgxEntry> servers = conf.findAll(NgxConfig.BLOCK,  "server");
        System.out.println(servers.size());
        for (NgxEntry entry : servers) {
            NgxBlock block = (NgxBlock) entry;
            NgxParam ngxParam = block.findParam("listen");
            System.out.println(ngxParam.getValue());
        }
    }
}
