import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * 1. Launch application.
 * 2. Select any file in the dialog.
 * 3. Inspect that dialog.getFilterIndex() returns a valid index.
 * 
 * Not patched version always returns -1.
 * 
 * Issue appears since GTK 3.22.17.
 * It is a regression from https://gitlab.gnome.org/GNOME/gtk/-/commit/291eda66755531cda4d1911e70ebefb5291d8e20
 * 
 * @see https://gitlab.gnome.org/GNOME/gtk/-/merge_requests/2069
 *
 */
public class FileDialogIssue {
	
    public static void main (String [] args) {
        Display display = new Display();
        
        System.out.println("GTK version: " + System.getProperty("org.eclipse.swt.internal.gtk.version"));
        
        Shell shell = new Shell(display);
        
        shell.setLayout(new RowLayout(SWT.HORIZONTAL));
        Button b1 = new Button(shell, SWT.NONE);
        b1.setText("Open...");
		b1.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event event) {
				FileDialog dialog = getFileDialog(shell);
				dialog.open();
				
				// Since GTK 3.22.17 this method always returns -1. 
				System.out.println("Filter Index : " + dialog.getFilterIndex());
			}
		});
		

		
        shell.pack();
		
        shell.open ();
        while (!shell.isDisposed ()) {
            if (!display.readAndDispatch ()) display.sleep ();
        }
        shell.dispose();
        display.dispose ();
    }
    
    
    private static FileDialog getFileDialog(Shell parent) {
        FileDialog dialog = new FileDialog(parent, SWT.SAVE);
        
        String[] filterExt = new String[]{ "*.jpg", "*.gif"};
        dialog.setFilterExtensions(filterExt);
        dialog.setFilterNames(new String[] {"pics", "anim"});
        
        dialog.setFilterIndex(1);
        
        return dialog;
    }
}
