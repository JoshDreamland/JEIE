/*
 * Copyright (C) 2012 IsmAvatar <IsmAvatar@gmail.com>
 * 
 * This file is part of Jeie.
 * Jeie is free software and comes with ABSOLUTELY NO WARRANTY.
 * See LICENSE for details.
 */

package org.jeie;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.jeie.Jeie.ToolDelegate;
import org.jeie.Tool.FillTool;
import org.jeie.Tool.LineTool;
import org.jeie.Tool.PointTool;
import org.jeie.Tool.RectangleTool;

public class ToolPanel extends JPanel implements ActionListener
	{
	private static final long serialVersionUID = 1L;
	protected ToolDelegate del;
	protected JPanel toolGrid;
	protected ButtonGroup bg = new ButtonGroup();

	public ToolPanel(ToolDelegate del)
		{
		super();
		this.del = del;

		add(toolGrid = new JPanel(new GridLayout(0,2)));

		AbstractButton sel;

		addButton(new ToolButton(Jeie.getIcon("pencil"),"Pencil - draws freehand strokes",
				new PointTool()));
		sel = addButton(new ToolButton(Jeie.getIcon("line"),"Line - draws a straight line",
				new LineTool()));
		addButton(new ToolButton(Jeie.getIcon("rect"),"Rect - draws a filled rectangle",
				new RectangleTool()));
		addButton(new ToolButton(Jeie.getIcon("color-fill"),"Fill - flood-fills a region",
				new FillTool()));

		// select our default button
		sel.doClick();
		}

	public <K extends AbstractButton>K addButton(K b)
		{
		toolGrid.add(b);
		bg.add(b);
		b.addActionListener(this);
		return b;
		}

	public class ToolButton extends JToggleButton
		{
		private static final long serialVersionUID = 1L;

		public final Tool tool;

		public ToolButton(ImageIcon ico, Tool t)
			{
			this(ico,null,t);
			}

		public ToolButton(ImageIcon ico, String tip, Tool t)
			{
			super(ico);
			tool = t;
			setToolTipText(tip);
			setPreferredSize(new Dimension(32,32));
			}
		}

	public void actionPerformed(ActionEvent e)
		{
		del.setTool(((ToolButton) e.getSource()).tool);
		return;
		}
	}