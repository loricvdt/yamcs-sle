package org.yamcs.sle;

import java.io.IOException;
import java.io.InputStream;

import org.yamcs.Plugin;
import org.yamcs.PluginException;
import org.yamcs.YamcsServer;
import org.yamcs.http.HttpServer;


public class SlePlugin implements Plugin {

    @Override
    public void onLoad() throws PluginException {
        HttpServer httpServer = YamcsServer.getServer().getGlobalServices(HttpServer.class).get(0);
        try (InputStream in = getClass().getResourceAsStream("/yamcs-sle.protobin")) {
            System.out.println("---------------------in: "+in);
            httpServer.getProtobufRegistry().importDefinitions(in);
        } catch (IOException e) {
            throw new PluginException(e);
        }
        System.out.println("bubu after---------------------in: ");
        httpServer.addApi(new SleApi());
    }
}