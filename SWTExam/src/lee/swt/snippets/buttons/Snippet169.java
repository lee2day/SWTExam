package lee.swt.snippets.buttons;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class Snippet169 {

	public static void main(String[] args) {
		Display display = new Display();
		final Shell shell = new Shell(display);
		shell.setLayout(new FillLayout());
		Listener listener = new Listener() {

			@Override
			public void handleEvent(Event e) {
				Control [] children = shell.getChildren();
				for (int i=0; i < children.length; i++) {
					Control child = children[i];
					if (e.widget != child && child instanceof Button && (child.getStyle() & SWT.TOGGLE) != 0) {
						((Button)child).setSelection(false);
					}
				}
				((Button)e.widget).setSelection(true);
			}
		};

		for (int i=0; i < 20; i++) {
			Button button = new Button(shell, SWT.TOGGLE);
			button.setText("B" + i);
			button.addListener(SWT.Selection, listener);
			if (i == 0) button.setSelection(true);
		}
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}

