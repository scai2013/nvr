package com.jeremydyer.service.runnable;

import com.jeremydyer.NVRConfiguration;
import com.jeremydyer.service.email.EmailManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;

/**
 * Created by jeremydyer on 3/22/15.
 */
public class StorageMetricsRunnable
    implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(StorageMetricsRunnable.class);
    private NVRConfiguration nvrConfiguration = null;
    private EmailManager em = null;

    public StorageMetricsRunnable(NVRConfiguration nvrConfiguration) {
        this.nvrConfiguration = nvrConfiguration;
        this.em = new EmailManager(nvrConfiguration);
    }

    @Override
    public void run() {
        logger.debug("StorageMetricsRunnable.run()");
        File f = new File(nvrConfiguration.getNvrVideoDir());
        String info = new Date() + " Total Usable Space: " + f.getUsableSpace() / (1024 * 1024 * 1024) + " GB for: " + f.getAbsolutePath();
        logger.info(info);
        this.em.sendEmail(info);
    }
}
