package cn.cdu.edu.TQC.simulation.ui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.Timer;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import cn.cdu.edu.TQC.simulation.bean.SAmmeter;
import cn.cdu.edu.TQC.simulation.bean.SMeter;
import cn.cdu.edu.TQC.simulation.dao.SAmmeterDao;
import cn.cdu.edu.TQC.simulation.dao.SMeterDao;
import cn.cdu.edu.TQC.simulation.timer.CollectAmmeterData;
import cn.cdu.edu.TQC.simulation.timer.CollectMeterData;
import cn.cdu.edu.TQC.simulation.timer.InitAmmeter;
import cn.cdu.edu.TQC.simulation.timer.InitMeter;
import cn.cdu.edu.TQC.simulation.timer.UpDateAmmeterTask;
import cn.cdu.edu.TQC.simulation.timer.UpDateMeterTask;

/**
 * @ClassName: Collection_Ammeter
 * 
 * @Description: TODO(Tell the reader such role.)
 * 
 * @Author LPM 【联系方式：shouli1990@gmail.com】
 * @Date 2012-6-14 下午3:04:25
 * 
 * @Version V1.0
 * 
 */
public class Similation {
    private JFrame frame = null;
    private JTable table = null;
    private Table_Model model = null;
    private JScrollPane s_pan = null;
    private JButton button_1 = null, button_2 = null, button_3 = null,
	    button_4 = null, button_5 = null;

    private Timer timer, timer1, timer2;
    private UpDateAmmeterTask task1;
    private UpDateMeterTask task2;
    private JPanel pane = null;
    private JDialog dialog = null;
    private JFrame jFrame = null;
    private int setValue = 3;
    private int type = 0;// 0、电表1、水表
    private JComboBox choice;

    private Timer timer_ammeter = null;
    private Timer timer_meter = null;
    private CollectAmmeterData task00 = null;
    private CollectMeterData task11 = null;

    public Similation() {
	frame = new JFrame("数据更新器");
	pane = new JPanel();

	String[] typ = { "电表", "水表" };
	choice = new JComboBox(typ);
	choice.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {
		String t = (String) e.getItem();
		if ("电表".equals(t)) {
		    type = 0;
		}
		if ("水表".equals(t)) {
		    type = 1;
		}
		// TODO 电话
	    }
	});

	button_1 = new JButton("重置数据");
	button_1.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int btn = JOptionPane.showConfirmDialog(null, "您将重置所有仪表数据?",
			"是否重置", JOptionPane.YES_NO_OPTION);
		if (btn == JOptionPane.YES_OPTION) {
		    // 保存功能代码
		    rest(type);
		} else if (btn == JOptionPane.NO_OPTION) {
		    return;
		}
	    }
	});
	button_2 = new JButton("查看数据");
	button_2.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		System.out.println("查看数据-------" + type);

		if (type == 0) {
		    SAmmeter[] ammeters = new SAmmeterDao().getAmmeters();
		    update(ammeters, type);
		}
		if (type == 1) {
		    SMeter[] meters = new SMeterDao().getMeters();
		    update(meters, type);
		}

	    }
	});
	button_3 = new JButton("保存修改");
	button_3.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		saveData(type);
	    }
	});
	button_4 = new JButton("数据更新计划");
	button_4.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		// TODO 在此发布任务 采集数据
		Dialog();

	    }
	});
	button_5 = new JButton("采集数据");
	button_5.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		upDateDalog();

	    }
	});

	pane.add(choice);
	pane.add(button_1);
	pane.add(button_2);
	pane.add(button_3);
	pane.add(button_4);
	pane.add(button_5);

	model = new Table_Model(20);
	table = new JTable(model);

	table.setBackground(Color.white);
	Integer[] floor = { 1, 2, 3, 4, 5, 6, 7, -1 };
	JComboBox com = new JComboBox(floor);
	TableColumnModel tcm = table.getColumnModel();
	tcm.getColumn(0).setPreferredWidth(150);
	tcm.getColumn(1).setPreferredWidth(150);
	tcm.getColumn(2).setPreferredWidth(150);

	s_pan = new JScrollPane(table);

	frame.getContentPane().add(s_pan, BorderLayout.CENTER);
	frame.getContentPane().add(pane, BorderLayout.NORTH);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600, 800);
	frame.setVisible(true);

    }

    /**
     * 更新所有电表数据
     * 
     * @param ammeters
     */
    private void update(Object[] objects, int type) {
	removeData();
	addData(objects, type);
    }

    /**
     * 重置所有仪表数据
     */
    private void rest(int type) {
	removeData();
	if (type == 0) {
	    SAmmeter[] ammeters = new InitAmmeter().getAmmeters();
	    addData(ammeters, type);
	}
	if (type == 1) {
	    SMeter[] meters = new InitMeter().getMeters();
	    addData(meters, type);
	}

    }

    /**
     * 添加一条数据
     * 
     * @param ammeter
     */
    private void addData(Object object, int type) {
	model.addRow(object, type);
	table.updateUI();
    }

    /**
     * 添加若干行数据
     * 
     * @param ammeters
     */
    private void addData(Object[] objects, int type) {
	for (Object object : objects) {
	    model.addRow(object, type);
	    table.updateUI();
	}
    }

    private void removeData() {
	model.removeRows(0, model.getRowCount());
	table.updateUI();
    }

    /**
     * 保存数据，暂时是将数据从控制台显示出来
     */
    private void saveData(int type) {
	int col = model.getColumnCount();
	int row = model.getRowCount();

	if (type == 0) {
	    SAmmeter[] ammeters = new SAmmeter[row];
	    for (int i = 0; i < col; i++) {
		System.out.print(model.getColumnName(i) + "\t");
	    }
	    System.out.print("\r\n");

	    for (int i = 0; i < row; i++) {
		// ID", "用量", "状态" , "楼层编号

		ammeters[i] = new SAmmeter();
		ammeters[i].setaID((String) model.getValueAt(i, 0));
		ammeters[i].setTotalNum((Double) model.getValueAt(i, 1));
		boolean state = (Boolean) model.getValueAt(i, 2);
		ammeters[i].setState((Boolean) model.getValueAt(i, 2));

		for (int j = 0; j < col; j++) {
		    System.out.print(model.getValueAt(i, j) + "\t");
		}
		System.out.print("\r\n");
	    }
	    new SAmmeterDao().addAmmeters(ammeters);
	}
	if (type == 1) {
	    SMeter[] meters = new SMeter[row];
	    for (int i = 0; i < col; i++) {
		System.out.print(model.getColumnName(i) + "\t");
	    }
	    System.out.print("\r\n");

	    for (int i = 0; i < row; i++) {
		// ID", "用量", "状态" , "楼层编号

		meters[i] = new SMeter();
		meters[i].setmID((String) model.getValueAt(i, 0));
		meters[i].setTotalNum((Double) model.getValueAt(i, 1));
		boolean state = (Boolean) model.getValueAt(i, 2);
		meters[i].setState((Boolean) model.getValueAt(i, 2));

		for (int j = 0; j < col; j++) {
		    System.out.print(model.getValueAt(i, j) + "\t");
		}
		System.out.print("\r\n");
	    }
	    new SMeterDao().addMeters(meters);
	}

	System.out.println("------------------------------------");
    }

    private void Dialog() {
	dialog = new JDialog();
	dialog.setBounds(280, 280, 200, 120);
	dialog.setTitle("系统设置");
	dialog.setLayout(new FlowLayout());

	dialog.add(new JLabel("设置数据根心频率（/秒）："));

	Integer[] day = { 1, 2, 3, 4, 5, 6, 7 };
	JComboBox jBox = new JComboBox(day);
	jBox.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED)// 需要加上这句，因为Change有DESELECTED和SELECTED两种情况
		    setValue = (Integer) e.getItem();
	    }
	});
	System.out.println("------------->" + setValue);
	dialog.add(jBox);

	JButton close = new JButton("确定");
	close.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		// 在此处添加定时器
		if (type == 0) {
		    if (timer1 == null && task1 == null) {
			// 电表更新
			timer1 = new Timer();
			task1 = new UpDateAmmeterTask();
			timer1.schedule(task1, 1000, setValue * 1000);
		    }

		} else {
		    // 水表更新
		    if (timer2 == null && task2 == null) {
			timer2 = new Timer();
			task2 = new UpDateMeterTask();
			timer2.schedule(task2, 1000, setValue * 1000);
		    }

		}

		dialog.dispose();
	    }
	});
	dialog.add(close);
	dialog.setVisible(true);

    }

    private void upDateDalog() {
	dialog = new JDialog();
	dialog.setBounds(280, 280, 200, 120);
	dialog.setTitle("系统设置");
	dialog.setLayout(new FlowLayout());

	dialog.add(new JLabel("设置数据采集频率（/秒）："));

	Integer[] day = { 1, 2, 3, 4, 5, 6, 7 };
	JComboBox jBox = new JComboBox(day);
	jBox.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED)// 需要加上这句，因为Change有DESELECTED和SELECTED两种情况
		    setValue = (Integer) e.getItem();
	    }
	});
	System.out.println("------------->" + setValue);
	dialog.add(jBox);

	JButton close = new JButton("确定");
	close.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		// 在此处添加定时器
		if (type == 0) {
		    if (timer_ammeter == null && task00 == null) {
			// 电表更新
			timer_ammeter = new Timer();
			task00 = new CollectAmmeterData();
			timer_ammeter.schedule(task00, 0, setValue * 1000);
		    }

		} else {
		    // 水表更新
		    if (timer_meter == null && task11 == null) {
			timer_meter = new Timer();
			task11 = new CollectMeterData();
			timer_meter.schedule(task11, 0, setValue * 1000);
		    }

		}

		dialog.dispose();
	    }
	});
	dialog.add(close);
	dialog.setVisible(true);

    }

    public static void main(String args[]) {
	new Similation();
	System.out
		.println("按下保存按钮将会把JTable中的内容显示出来\r\n------------------------------------");
    }
}

class Table_Model extends AbstractTableModel {

    private static final long serialVersionUID = -3295940408592595397L;
    private Vector content = null;
    private String[] title_name = { "ID", "用量", "状态" };

    public Table_Model() {
	content = new Vector();
    }

    public Table_Model(int count) {
	content = new Vector(count);
    }

    public void addRow(Object obj, int type) {
	Vector v = new Vector(3);
	if (type == 0) {
	    SAmmeter ammeter = (SAmmeter) obj;
	    v.add(0, ammeter.getaID());
	    v.add(1, ammeter.getTotalNum());
	    v.add(2, ammeter.isState());
	}
	if (type == 1) {
	    SMeter meter = (SMeter) obj;
	    v.add(0, meter.getmID());
	    v.add(1, meter.getTotalNum());
	    v.add(2, meter.isState());
	}
	content.add(v);
    }

    public void removeRow(int row) {
	content.remove(row);
    }

    public void removeRows(int row, int count) {
	for (int i = 0; i < count; i++) {
	    if (content.size() > row) {
		content.remove(row);
	    }
	}
    }

    /**
     * 让表格中某些值可修改，但需要setValueAt(Object value, int row, int col)方法配合才能使修改生效
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	if (columnIndex == 0) {
	    return false;
	}
	return true;
    }

    /**
     * 使修改的内容生效
     */
    public void setValueAt(Object value, int row, int col) {
	((Vector) content.get(row)).remove(col);
	((Vector) content.get(row)).add(col, value);
	this.fireTableCellUpdated(row, col);
    }

    public String getColumnName(int col) {
	return title_name[col];
    }

    public int getColumnCount() {
	return title_name.length;
    }

    public int getRowCount() {
	return content.size();
    }

    public Object getValueAt(int row, int col) {
	return ((Vector) content.get(row)).get(col);
    }

    /**
     * 返回数据类型
     */
    public Class getColumnClass(int col) {
	return getValueAt(0, col).getClass();
    }

}
