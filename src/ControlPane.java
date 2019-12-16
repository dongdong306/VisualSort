import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class ControlPane extends Pane {
	Agent agent;
	Pane mainPane;
	Pane inputPane;
	Pane settingPane;

	public ControlPane(Agent agent) {
		this.agent = agent;
		
		
		
		initMainPane();
		initInputPane();
		initSettingPane();
		
		super.getChildren().addAll(mainPane, inputPane, settingPane);
		
	}
	
	
	private void initMainPane() {
		this.mainPane = new Pane();
		// 选择框内容
		Label label = new Label("排序算法");
		label.setFont(new Font(15));
		ComboBox<String> comboBox = new ComboBox<String>(
		FXCollections.observableArrayList("冒泡排序", "插入排序", "选择排序", "快速排序"));
		comboBox.setValue("冒泡排序");
		HBox hBox = new HBox();
		hBox.setLayoutX(10);
		hBox.setLayoutY(30);
		hBox.setSpacing(20);
		hBox.getChildren().addAll(label, comboBox);

		// 开始按钮
		Button btStart = new Button("开始");
		btStart.setPrefHeight(40);
		btStart.setPrefWidth(80);
		btStart.setLayoutX(15);
		btStart.setLayoutY(100);
		btStart.setOnAction(E -> {
			agent.setWayOfSort(comboBox.getValue());
			agent.play();
		});

		// 暂停按钮
		Button btPause = new Button("暂停");
		btPause.setPrefHeight(40);
		btPause.setPrefWidth(80);
		btPause.setLayoutX(105);
		btPause.setLayoutY(100);
		btPause.setOnAction(E -> {
		agent.pause();
				});

		// 输入数据按钮
		Button btWriteData = new Button("输入数据");
		btWriteData.setPrefHeight(40);
		btWriteData.setPrefWidth(80);
		btWriteData.setLayoutX(15);
		btWriteData.setLayoutY(200);
		btWriteData.setOnAction(E -> {
		mainPane.setVisible(false);
		inputPane.setVisible(true);
		});

		// 随机数据按钮
		Button btRandomData = new Button("随机数据");
		btRandomData.setPrefHeight(40);
		btRandomData.setPrefWidth(80);
		btRandomData.setLayoutX(105);
		btRandomData.setLayoutY(200);
		btRandomData.setOnAction(E -> {
			agent.stop();
			agent.randomData();
			agent.draw();
		});

		// 恢复按钮
		Button btRecover = new Button("恢复");
		btRecover.setPrefHeight(40);
		btRecover.setPrefWidth(80);
		btRecover.setLayoutX(15);
		btRecover.setLayoutY(300);
		btRecover.setOnAction(E -> {
			agent.stop();
			agent.recover();
			agent.draw();
		});

		// 保存数据按钮
		Button btSave = new Button("保存数据");
		btSave.setPrefHeight(40);
		btSave.setPrefWidth(80);
		btSave.setLayoutX(105);
		btSave.setLayoutY(300);
		btSave.setOnAction(E -> {
			try {
				agent.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		
		Button btSetting = new Button("设置");
		btSetting.setPrefHeight(50);
		btSetting.setPrefWidth(100);
		btSetting.setLayoutX(50);
		btSetting.setLayoutY(400);
		btSetting.setOnAction(E -> {
			mainPane.setVisible(false);
			settingPane.setVisible(true);
		});
				
		//将按钮加入右侧的控制面板
		mainPane.getChildren().addAll(btStart, 
				btPause, 
				btWriteData, 
				btRandomData, 
				btRecover, 
				btSave, 
				btSetting,
				hBox
				);
	}
	
	private void initInputPane() {
		this.inputPane = new Pane();
		
		Label tip = new Label("请输入整数以空格分隔");
		tip.setLayoutX(20);
		tip.setLayoutY(20);
		
//		TextField textData = new TextField();
		TextArea textData = new TextArea();
		textData.setPrefHeight(40);
		textData.setPrefWidth(160);
		//textData.setPrefSize(10, 6);
		textData.setLayoutX(20);
		textData.setLayoutY(50);
		
		
		//输入数据页面的确认按钮
		Button btSure = new Button("确定");
		btSure.setPrefHeight(40);
		btSure.setPrefWidth(80);
		btSure.setLayoutX(15);
		btSure.setLayoutY(150);
		btSure.setOnAction(E -> {
			inputPane.setVisible(false);
			mainPane.setVisible(true);
			agent.stop();
			String[] temp = textData.getText().split(" ");
			agent.textInput(temp);
			agent.draw();
		});
		
		Button btBack = new Button("返回");
		btBack.setPrefHeight(40);
		btBack.setPrefWidth(80);
		btBack.setLayoutX(105);
		btBack.setLayoutY(150);
		btBack.setOnAction(E -> {
			inputPane.setVisible(false);
			mainPane.setVisible(true);
		});
		
		inputPane.getChildren().addAll(tip, textData, btSure, btBack);
		inputPane.setVisible(false);
	}
	
	private void initSettingPane() {
		this.settingPane = new Pane();
		
		Label label1 = new Label("动画速率");
		label1.setFont(new Font(15));
		ComboBox<String> comboBox1 = new ComboBox<String>(
				FXCollections.observableArrayList("1", "2", "4" , "10"));
		comboBox1.setValue("1");
		GridPane gridPane = new GridPane();
		gridPane.setLayoutX(5);
		gridPane.setLayoutY(30);
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		
		gridPane.add(label1, 0, 0);
		gridPane.add(comboBox1, 1, 0);
		
		Label label2 = new Label("随机数据数量范围");
		label2.setFont(new Font(15));
		ComboBox<String> comboBox2 = new ComboBox<String>(
				FXCollections.observableArrayList("10", "20", "30", "50", "100", "150","200", "250", "300"));
		comboBox2.setValue("20");
		
		gridPane.add(label2, 0, 1);
		gridPane.add(comboBox2, 1, 1);
		
		Button btSure = new Button("确定");
		btSure.setPrefHeight(40);
		btSure.setPrefWidth(80);
		btSure.setLayoutX(15);
		btSure.setLayoutY(150);
		btSure.setOnAction(E -> {
			int temp = 200;
			if (comboBox1.getValue().equals("1")) {
				temp = 200;
			}
			else if (comboBox1.getValue().equals("2")) {
				temp = 100;
			}
			else if (comboBox1.getValue().equals("4")) {
				temp = 50;
			}
			else if (comboBox1.getValue().equals("10")) {
				temp = 20;
			}
			agent.setDuration(temp);
			agent.setRandomDataSize(Integer.valueOf(comboBox2.getValue()));
			settingPane.setVisible(false);
			mainPane.setVisible(true);
		});
		
		Button btBack = new Button("返回");
		btBack.setPrefHeight(40);
		btBack.setPrefWidth(80);
		btBack.setLayoutX(105);
		btBack.setLayoutY(150);
		btBack.setOnAction(E -> {
			settingPane.setVisible(false);
			mainPane.setVisible(true);
		});
		
		
		settingPane.getChildren().addAll(gridPane, btSure, btBack);
		
		settingPane.setVisible(false);
	}
}
