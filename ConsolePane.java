package rogue;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ConsolePane extends JPanel{
	private JTextArea box = new JTextArea();
	private ArrayList<String> Log = new ArrayList<String>();
	private int index = 0;
	private int MAXindex = 7;



	public ConsolePane() {
		//テキストボックス 様々なメッセージ出力用
		box.setPreferredSize(new Dimension(760, 180));
		this.add(box);
		this.box.append("＠　：　あなた");
		this.box.append("\n");

		for(int j=0; j<MAXindex; j++) {
			this.Log.add("");
		}

		this.rewriteStatus(Main.YOU);
	}


	public void rewriteStatus(Player c) {
		this.box.setText("");
		this.box.append("HP : " + c.getHP() + "/" + c.getMAXHP() +
				"  MP : " + c.getMP() + "/" + c.getMAXMP() + "  Hungry : "+  c.getHUNG() +
				"  FLOOR : " + c.getFLOOR() + "B  " + "EXP : " + c.getEXP() + "/" + c.getMAXEXP() + "\n");
		this.box.append("-------------------------------------------------------\n");
	}

	public void makeLog(String s) {
		this.rewriteStatus(Main.YOU);
		if(6 < this.index) {
			this.Log.set(0, s);
			this.index = 1;
		}else {
			this.Log.set(this.index, s);
			this.index++;
		}
		for(int i=this.index; i<MAXindex; i++) {
			this.box.append(this.Log.get(i));
		}
		for(int  i=0; i<this.index; i++) {
			this.box.append(this.Log.get(i));
		}
	}
}
