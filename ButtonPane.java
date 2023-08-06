package rogue;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

public class ButtonPane extends JPanel implements ActionListener,  ItemListener{
	public CardLayout layout = new CardLayout();
	//カード2　ボタン類
	JButton kita = new JButton("↑");
	JButton higashi = new JButton("→");
	JButton nishi = new JButton("←");
	JButton minami = new JButton("↓");
	JButton center = new JButton("SERCH");
	JButton aitemu_move = new JButton("ITEMS");
	JButton magic = new JButton("");

	//カード3　ボタン類
	JButton tatakau = new JButton("COMBAT");
	JButton aitemu = new JButton("ITEMS");
	JButton nigeru = new JButton("ESCAPE");

	//カード4　ボタン類
	JButton tukau = new JButton("USE");
	JButton modoru = new JButton("←");
    JComboBox<String> combo = new JComboBox<String>();
    Iteam combo_selected;


	public ButtonPane(){
		this.setLayout(layout);

		//カードレイアウト１　メインメニュー
		//主にこちらを表示しておく
		//使わない予定
		JPanel card1 = new JPanel();
		JButton tansaku = new JButton("探索");
		JButton ido = new JButton("移動");
		JButton items = new JButton("アイテム");
		JButton exit = new JButton("システム");
		card1.setLayout(new GridLayout(4,1));
		card1.add(tansaku);
		card1.add(ido);
		card1.add(items);
		card1.add(exit);



		//カードレイアウト２　移動用メニュー
		//移動ボタン押し後，表示
		//常に表示に変更したい...
		JPanel card2 = new JPanel();
		card2.setLayout(new BorderLayout());
		JPanel naka = new JPanel();
		naka.setLayout(new GridLayout(3,1));
		naka.add(center);
		naka.add(aitemu_move);
		naka.add(magic);
		/*
	    card2.add("North", kita);
	    card2.add("East", higashi);
	    card2.add("West", nishi);
	    card2.add("South", minami);
	    card2.add("Center", center);
		 */
		card2.add("North", kita);
	    card2.add("East", higashi);
	    card2.add("West", nishi);
	    card2.add("South", minami);
	    card2.add("Center", naka);

	    //カードレイアウト３　戦闘用メニュー
	    JPanel card3 = new JPanel();
	    card3.setLayout(new GridLayout(3,1));
	    card3.add(tatakau);
	    card3.add(aitemu);
	    card3.add(nigeru);

	    //カードレイアウト４　アイテム使用メニュー
	    JPanel card4 = new JPanel();
	    card4.setLayout(new GridLayout(3,1));
	    card4.add(combo);
	    card4.add(tukau);
	    card4.add(modoru);

	    //カードレイアウトに追加
	    //新しいパネルはこちらから
	    this.add(card1, "menu");
	    this.add(card2, "move");
	    this.add(card3, "combat");
	    this.add(card4, "item_m");


	    //カードの切り替え分岐
	    //ボタン押して切り替え
	    //戦闘時は強制切り替えの予定
	    layout.show(this, "move");



	    //アクションリスナー登録
	    this.kita.addActionListener(this);
		this.minami.addActionListener(this);
		this.higashi.addActionListener(this);
		this.nishi.addActionListener(this);
		this.center.addActionListener(this);
		this.tatakau.addActionListener(this);
		this.aitemu.addActionListener(this);
		this.nigeru.addActionListener(this);
		this.aitemu_move.addActionListener(this);
		this.magic.addActionListener(this);
		this.tukau.addActionListener(this);
		this.modoru.addActionListener(this);
		this.combo.addItemListener(this);
	}


	//カード切り替え
	public void combatMode() {
		this.layout.show(this, "combat");
	}
	public void moveMode() {
		this.layout.show(this, "move");
	}
	public void menuMode() {
		this.layout.show(this, "menu");
	}
	public void iteamMode() {
		this.layout.show(this, "item_m");
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == kita){
			Main.YOU.move(1);
		}
		if(e.getSource() == minami){
			Main.YOU.move(2);
		}
		if(e.getSource() == higashi){
			Main.YOU.move(3);
		}
		if(e.getSource() == nishi){
			Main.YOU.move(4);
		}
		if(e.getSource() == center){
			int newXY[] = new int[2];
			newXY = Main.YOU.getCharXY();
			if(Main.DUNGEON.getMapEvent(newXY[0], newXY[1]) == "∨") {			//次の階層
				Map newM = new Map(1);
				Main.DUNGEON = newM;
				newXY = Main.DUNGEON.choiceone();
				Main.YOU.setCharXY(newXY[0], newXY[1]);
				Main.map.rewriteMap(newXY[0], newXY[1]);
				Main.YOU.addFLOOR();
				Main.console.makeLog("[System_Log]You got down next floor...\n");
			}else if(Main.DUNGEON.getMapEvent(newXY[0], newXY[1]) == "ヨ"){
				Main.EM.random_Eve(Main.YOU);
				Main.DUNGEON.changeStatus(Main.YOU.getCharXY()[0], Main.YOU.getCharXY()[1] , "＿");
			}else {
				Main.console.makeLog("[System_Log]There seems to be nothing here.\n");
			}
		}
		if(e.getSource() == aitemu_move){										//移動中アイテムメニュー
			String tmp;
			this.combo.removeAllItems();
			HashMap<Integer, Iteam> list = Main.IM.getIteamMenu();
			for(int i=0; i<255; i++) {
				tmp = list.get(i).getITEAMName();
				if(tmp != "DUMMY") {											//所持中かつダミーじゃないもの
					if(list.get(i).getITEAMhas() != 0) {
						combo.addItem(tmp);
					}
				}
			}
			this.iteamMode();
		}
		if(e.getSource() == tukau){
			this.combo_selected.IteamUse(Main.YOU);				//回復アイテム系なら使用
		}															//装備系なら装着
		if(e.getSource() == modoru){
			this.moveMode();
		}
		if(e.getSource() == magic){
			System.out.println("DON'T IMPLEMENT. ");
		}
		if(e.getSource() == tatakau){
			Main.CM.attack();
		}
		if(e.getSource() == aitemu){
			System.out.println("DON'T IMPLEMENT. ");
		}
		if(e.getSource() == nigeru){
			Main.CM.escape();
		}
	}


	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getStateChange() == e.SELECTED) {
			String item_name = (String) this.combo.getSelectedItem();
			this.combo_selected = Main.IM.getIteam(item_name);
		}
	}
}

