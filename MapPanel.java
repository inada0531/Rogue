package rogue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MapPanel extends JPanel{
	JTextArea box = new JTextArea();
	private Map DUNGEON;				//パネル呼び出し時にメインマップ作成

	public MapPanel(Map DUNGEON) {
		this.DUNGEON = DUNGEON;
		int newXY[] = new int[2];
		newXY = this.DUNGEON.choiceone();
		Main.YOU.setCharXY(newXY[0], newXY[1]);
		//テキストボックス マップ表示用
		this.box.setPreferredSize(new Dimension(500, 400));
		//マップ背景色：黒
		this.box.setBackground(Color.BLACK);
		this.add(box);
		//マップ文字色：緑
		this.box.setForeground(Color.GREEN);
		//this.box.append("Hello!");
		//this.box.append("\n");
		//this.box.append("Hello!");
		this.box.setText("");							//空白で上書き
		String temp[][] = this.DUNGEON.getMap();
		for(int j=0; j<23; j++) {
			for(int i=0; i<41; i++) {
				if(i == newXY[0] && j == newXY[1]) {
					//this.box.setForeground(Color.RED);
					this.box.append("＠");
					//this.box.setForeground(Color.GREEN);
				}else {
					this.box.append(temp[i][j]);
				}
			}
			this.box.append("\n");
		}


	}


	//マップを取得し，画面をリフレッシュする
	public void rewriteMap(int charX, int charY) {
		this.box.setText("");							//空白で上書き
		String temp[][] = this.DUNGEON.getMap();
		for(int j=0; j<23; j++) {
			for(int i=0; i<41; i++) {
					if(i == charX && j == charY) {				//キャラクターの現在地点の場合，赤字で＊
						this.box.append("＠");
					}else {
						this.box.append(temp[i][j]);			//それ以外の場合
					}
			}
			this.box.append("\n");
		}
	}

	//新しいマップを取得する　この後MapPaneのrewriteMap()をする
	public void newMap(Map DUNGEON) {

	}

}
