package masterFamine.functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.powerbot.game.api.util.Timer;

//TODO: Make a new GUI with info and everything.
public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean isRunning = true;

	public GUI() {
		setResizable(true);
		setAlwaysOnTop(true);
		setTitle("MasterFamine");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 220, 270);
		Constants.contentPane = new JPanel();
		Constants.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Constants.contentPane);
		Constants.contentPane.setLayout(null);
		// Constants.contentPane.setLayout(null);

		final JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Eating");
		chckbxNewCheckBox_1.setBounds(8, 23, 101, 23);
		Constants.contentPane.add(chckbxNewCheckBox_1);

		final JCheckBox chckbxNewCheckBox = new JCheckBox("Excalibur");
		chckbxNewCheckBox.setBounds(8, 46, 101, 23);
		Constants.contentPane.add(chckbxNewCheckBox);

		final JCheckBox chckbxGloves = new JCheckBox("Gloves");
		chckbxGloves.setBounds(8, 69, 101, 23);
		Constants.contentPane.add(chckbxGloves);

		final JCheckBox chckbxDropping = new JCheckBox("Dropping");
		chckbxDropping.setBounds(8, 93, 101, 23);
		Constants.contentPane.add(chckbxDropping);

		final JCheckBox chckbxBanking = new JCheckBox("Banking");
		chckbxBanking.setBounds(8, 117, 101, 23);
		Constants.contentPane.add(chckbxBanking);

		@SuppressWarnings({ "unchecked", "rawtypes" })
		// TODO: Fix lazyness.
		final JComboBox comboBox = new JComboBox(Constants.foodNames);
		comboBox.setBounds(69, 143, 133, 27);
		Constants.contentPane.add(comboBox);

		JLabel lblFoodId = new JLabel("Food ID");
		lblFoodId.setBounds(10, 147, 61, 16);
		Constants.contentPane.add(lblFoodId);

		final JSlider slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(10);
		slider.setMaximum(100);
		slider.setBounds(59, 168, 154, 29);
		Constants.contentPane.add(slider);

		JLabel lblHeal = new JLabel("Heal %");
		lblHeal.setBounds(10, 175, 61, 16);
		Constants.contentPane.add(lblHeal);

		JButton btnStart = new JButton("Start");
		btnStart.setBounds(6, 209, 117, 29);
		Constants.contentPane.add(btnStart);
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Constants.HealPct = slider.getValue();
				System.out.println("Healpct : " + Constants.HealPct);
				if (chckbxBanking.isSelected() == true) {
					Constants.Banking = true;
					System.out.println("Banking true");
				}
				if (chckbxBanking.isSelected() == false) {
					Constants.Banking = false;
					Constants.invPrepped = true;
					Constants.Launch = false;
					System.out.println("Banking false --> invprepped true");
				}
				if (chckbxDropping.isSelected() == true) {
					Constants.Dropping = true;
					System.out.println("Dropping true");
				}
				if (chckbxNewCheckBox_1.isSelected() == true) {
					Constants.Eating = true;
					System.out.println("Eating true");
				}
				if (chckbxGloves.isSelected() == true) {
					Constants.Gloves = true;
					System.out.println("Gloves true");
				}
				if (chckbxNewCheckBox.isSelected() == true) {
					Constants.Excalibur = true;
					System.out.println("Excalibur true");
				}
				if (comboBox.getSelectedItem().equals("Trout")) {
					Constants.FoodID = 333;
					System.out.println("Food is Trout");
				}
				if (comboBox.getSelectedItem().equals("Tuna")) {
					Constants.FoodID = 361;
					System.out.println("Food is Tuna");
				}
				if (comboBox.getSelectedItem().equals("Lobster")) {
					Constants.FoodID = 379;
					System.out.println("Food is Lobster");
				}
				if (comboBox.getSelectedItem().equals("Swordfish")) {
					Constants.FoodID = 373;
					System.out.println("Food is Swordfish");
				}
				if (comboBox.getSelectedItem().equals("Monkfish")) {
					Constants.FoodID = 7946;
					System.out.println("Food is Monkfish");
				}
				if (comboBox.getSelectedItem().equals("Wine")) {
					Constants.FoodID = 1993;
					System.out.println("Drink is Wine");
				}

				isRunning = false;
				Constants.GUIFinished = true;
				Constants.StartTime = System.currentTimeMillis();
				Constants.runTime = new Timer(0);
				dispose();
			}
		});
	}

	public boolean isRunning() {
		return isRunning;
	}

}
