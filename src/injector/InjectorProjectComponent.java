package injector;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileAdapter;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;

public class InjectorProjectComponent implements ProjectComponent {

    protected Project project;
    private static final Logger LOG = Logger.getInstance("InjectorPlugin");
    private static final String INJECTOR_CONFIG_PATH = "injector.config";
    private InjectorConfig injectorConfig;

    public InjectorProjectComponent(Project project) {
        this.project = project;
    }

    @Override
    public void projectOpened() {
        this.validateAndWatch();
    }

    @Override
    public void initComponent() {
        this.validateAndWatch();
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "InjectorProjectComponent";
    }

    @Override
    public void projectClosed() {}

    @Override
    public void disposeComponent() {}

    private VirtualFile getConfigFile() {
        return this.project.getBaseDir().findFileByRelativePath(INJECTOR_CONFIG_PATH);
    }

    private void parseConfig() {
        VirtualFile configFile = this.getConfigFile();
        if (configFile == null || !configFile.exists()) {
            this.showInfoNotification("Config file not found in [<PROJECT_ROOT>/injector.config]", NotificationType.ERROR);
        } else {
            this.injectorConfig = new InjectorConfig(configFile);
        }
    }

    public boolean validateSettings() {
        // ensure config

    }

    private void validateAndWatch() {
        if (this.validateSettings()) {
            this.watchConfigFile();
        }
    }

    public void watchConfigFile() {
        // Add the Virtual File listener
        InjectorVfsListener vfsListener = new InjectorVfsListener();
        VirtualFileManager.getInstance().addVirtualFileListener(vfsListener, project);
    }

    public void showInfoNotification(String content, NotificationType type) {
        Notification errorNotification = new Notification("Require.js plugin", "Require.js plugin", content, type);
        Notifications.Bus.notify(errorNotification, this.project);
    }


    private class InjectorVfsListener extends VirtualFileAdapter {
        public void contentsChanged(@NotNull VirtualFileEvent event) {
            VirtualFile confFile = getConfigFile();
            if (confFile == null || !confFile.exists() || !event.getFile().equals(confFile)) {
                return;
            }
            LOG.debug("Injector config changed");
            InjectorProjectComponent.this.parseConfig();
        }
    }

}
