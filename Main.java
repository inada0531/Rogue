package rogue;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Main {
	//キャラクター作成
	static Player YOU = new Player(0,0);
	//初期マップ作成
	static Map DUNGEON = new Map(1);
	//コンバットマネージャー
	static Combat CM = new Combat();
	//モンスターマネージャー
	static MonsterManager MM = new MonsterManager();
	//アイテムマネージャー
	static IteamManager IM = new IteamManager();
	//イベントマネージャー
	static Event EM = new Event();



	//上半分パネル
	static JPanel m1 = new JPanel();
	//左上マップパネル　m1に乗せる
	static MapPanel map = new MapPanel(DUNGEON);
	//右上コマンド選択パネル　m1に乗せる
	static ButtonPane button = new ButtonPane();
	//下半分パネル
	static JPanel m2 = new JPanel();
	//下コンソール出力パネル　m2に乗せる
	static ConsolePane console = new ConsolePane();


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				// フレームの設定関連
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(800, 600);
				frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

				m1.setMaximumSize(new Dimension(800, 400));
				m1.setLayout(new BoxLayout(m1, BoxLayout.X_AXIS));

				map.setMaximumSize(new Dimension(500, 400));

				button.setMaximumSize(new Dimension(300, 400));
				m1.add(map);
				m1.add(button);

				m2.setMaximumSize(new Dimension(800, 200));

				m2.add(console);


				frame.getContentPane().add(m1);
				frame.getContentPane().add(m2);

				int newXY[] = new int[2];
				newXY = DUNGEON.choiceone();
				YOU.setCharXY(newXY[0], newXY[1]);
				map.rewriteMap(newXY[0], newXY[1]);

				frame.setVisible(true);
			}
		});
	}

}
