package finalproject;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

public class FinalProject extends JLayeredPane implements ActionListener{
	
	private static final long serialVersionUID = 1L; //Eclipse wanted this to be created
	
    private Synthesizer synthesizer; //All fields for the synthesizer
    private MidiChannel channel;
    public int noteIndex;
    private int intPatch = 1;
    private Instrument[] instr;
    public int octave = 3; 
    
    private List<Integer> buttonsWhite = new ArrayList<>(); //all fields for creating the GUI
    private List<JButton> keys = new ArrayList<>();
    private List<JButton> octaveButtons = new ArrayList<>();

    public static void wait(int ms) { //needed for the synth patch as without this it would sustain forever
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
	public FinalProject() {
		setVisible(true);
		
		for (int i = 0; i < 21; i++) {
			buttonsWhite.add(i);
		}

		for (int i = 0; i < 21; i++) {
			JButton button = new JButton();
            int whiteKeyInt = i;

			button.addActionListener(new ActionListener( ) {
				@Override
				public void actionPerformed(ActionEvent e) {
                    if (whiteKeyInt == 0) {
                        noteIndex = 24;
                    } if (whiteKeyInt == 1) {
                        noteIndex = 26;
                    } if (whiteKeyInt == 2) {
                        noteIndex = 28;
                    } if (whiteKeyInt == 3) {
                        noteIndex = 29;
                    } if (whiteKeyInt == 4) {
                        noteIndex = 31;
                    } if (whiteKeyInt == 5) {
                        noteIndex = 33;
                    } if (whiteKeyInt == 6) {
                        noteIndex = 35;
                    } if (whiteKeyInt == 7) {
                        noteIndex = 36;
                    } if (whiteKeyInt == 8) {
                        noteIndex = 38;
                    } if (whiteKeyInt == 9) {
                        noteIndex = 40;
                    } if (whiteKeyInt == 10) {
                        noteIndex = 41;
                    } if (whiteKeyInt == 11) {
                        noteIndex = 43;
                    } if (whiteKeyInt == 12) {
                        noteIndex = 45;
                    } if (whiteKeyInt == 13) {
                        noteIndex = 47;
                    } if (whiteKeyInt == 14) {
                        noteIndex = 48;
                    } if (whiteKeyInt == 15) {
                        noteIndex = 50;
                    } if (whiteKeyInt == 16) {
                        noteIndex = 52;
                    } if (whiteKeyInt == 17) {
                        noteIndex = 53;
                    } if (whiteKeyInt == 18) {
                        noteIndex = 55;
                    } if (whiteKeyInt == 19) {
                        noteIndex = 57;
                    } if (whiteKeyInt == 20) {
                        noteIndex = 59;
                    }
                    playNote("C");
				}
			});
			button.setBackground(Color.WHITE);
			button.setLocation(125 + i * 20, 100);
			button.setSize(20, 120);
			add(button, 0, -1);
			if (buttonsWhite.contains(i))
				keys.add(button);
		}

		for (int i = 0; i < 20; i++) {
			if (i == 2 || i == 6 || i == 9 || i == 13 || i == 16) {
				continue;
			}
			JButton button = new JButton();

            int blackKeyInt = i;

			button.addActionListener(new ActionListener( ) {
				@Override
				public void actionPerformed(ActionEvent e) {
					//playNote(Integer.toString(blackKeyInt));
                    if (blackKeyInt == 0) {
                        noteIndex = 25;
                    } if (blackKeyInt == 1) {
                        noteIndex = 27;
                    } if (blackKeyInt == 3) {
                        noteIndex = 30;
                    } if (blackKeyInt == 4) {
                        noteIndex = 32;
                    } if (blackKeyInt == 5) {
                        noteIndex = 34;
                    } if (blackKeyInt == 7) {
                        noteIndex = 37;
                    } if (blackKeyInt == 8) {
                        noteIndex = 39;
                    } if (blackKeyInt == 10) {
                        noteIndex = 42;
                    } if (blackKeyInt == 11) {
                        noteIndex = 44;
                    } if (blackKeyInt == 12) {
                        noteIndex = 46;
                    } if (blackKeyInt == 14) {
                        noteIndex = 49;
                    } if (blackKeyInt == 15) {
                        noteIndex = 51;
                    } if (blackKeyInt == 17) {
                        noteIndex = 54;
                    } if (blackKeyInt == 18) {
                        noteIndex = 56;
                    } if (blackKeyInt == 19) {
                        noteIndex = 58;
				    }
                    playNote(Integer.toString(blackKeyInt));
                }
			});
			button.setBackground(Color.BLACK);
			button.setLocation(137 + i * 20, 100);
			button.setSize(15, 70);
			add(button, 1, -1);
		}
		
        JButton inst1 = new JButton("Grand Piano");
        JButton inst2 = new JButton("Elec. Piano");
        JButton inst3 = new JButton("Acou. Guitar");
        JButton inst4 = new JButton("Organ");
        JButton inst5 = new JButton("Synthesizer");
        inst1.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
            	inst1.setBackground(Color.GRAY);
            	inst2.setBackground(Color.WHITE);
            	inst3.setBackground(Color.WHITE);
            	inst4.setBackground(Color.WHITE);
            	inst5.setBackground(Color.WHITE);
                System.out.println("Grand Piano Selected");
                intPatch = 1;
            }
        });
        inst1.setBackground(Color.GRAY);
        inst1.setLocation(20, 20);
		inst1.setSize(90, 30);
        add(inst1, 1, -1);

        
            inst2.addActionListener(new ActionListener( ) {
                @Override
                public void actionPerformed(ActionEvent e) {
                	inst1.setBackground(Color.WHITE);
                	inst2.setBackground(Color.GRAY);
                	inst3.setBackground(Color.WHITE);
                	inst4.setBackground(Color.WHITE);
                	inst5.setBackground(Color.WHITE);
                    System.out.println("Elec. Piano Selected");
                    intPatch = 5;
                }
            });
        inst2.setBackground(Color.WHITE);
        inst2.setLocation(20, 60);
		inst2.setSize(90, 30);
        add(inst2, 1, -1);

        
        inst3.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
            	inst1.setBackground(Color.WHITE);
            	inst2.setBackground(Color.WHITE);
            	inst3.setBackground(Color.GRAY);
            	inst4.setBackground(Color.WHITE);
            	inst5.setBackground(Color.WHITE);
                System.out.println("Acou. Guitar Selected");
                intPatch = 25;
            }
        });
        inst3.setBackground(Color.WHITE);
        inst3.setLocation(20, 100);
		inst3.setSize(90, 30);
        add(inst3, 1, -1);

        
        inst4.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
            	inst1.setBackground(Color.WHITE);
            	inst2.setBackground(Color.WHITE);
            	inst3.setBackground(Color.WHITE);
            	inst4.setBackground(Color.GRAY);
            	inst5.setBackground(Color.WHITE);
                System.out.println("Organ Selected");
                intPatch = 19;
            }
        });
        inst4.setBackground(Color.WHITE);
        inst4.setLocation(20, 140);
		inst4.setSize(90, 30);
        add(inst4, 1, -1);

        
        inst5.addActionListener(new ActionListener( ) {
            @Override
            public void actionPerformed(ActionEvent e) {
            	inst1.setBackground(Color.WHITE);
            	inst2.setBackground(Color.WHITE);
            	inst3.setBackground(Color.WHITE);
            	inst4.setBackground(Color.WHITE);
            	inst5.setBackground(Color.GRAY);
                System.out.println("Synthesizer Selected");
                intPatch = 63;
            }
        });
        inst5.setBackground(Color.WHITE);
        inst5.setLocation(20, 180);
		inst5.setSize(90, 30);
        add(inst5, 1, -1);

		JLabel octaveString = new JLabel("Octave");
		octaveString.setSize(50,10);
		octaveString.setLocation(313, 17);
		add(octaveString);
		
		for (int i = 0; i < 7; i++) {
			JButton button = new JButton("" + i);
            int currentOctave = i;
            
			button.addActionListener(new ActionListener( ) {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Octave = " + currentOctave);
                    octave = currentOctave;
                    octaveColor();
				}
			});
			if (i == 3)
				button.setBackground(Color.GRAY);
			else
				button.setBackground(Color.WHITE);
			button.setLocation(125 + i * 62, 40);
			button.setSize(50, 50);
			octaveButtons.add(button);
			add(button, 1, -1);
		}
	}

    private void startSynthesizer()  {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();

            instr = synthesizer.getDefaultSoundbank().getInstruments();

            channel = synthesizer.getChannels()[0];

            channel.programChange(instr[intPatch].getPatch().getProgram());
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
	
	public void octaveColor() {
		for (int i = 0; i < octaveButtons.size(); i++) {
			if (i == octave)
				octaveButtons.get(i).setBackground(Color.GRAY);
			else 
				octaveButtons.get(i).setBackground(Color.WHITE);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, Color.MAGENTA, w, h, Color.ORANGE);
        g2.setPaint(gp);
        g2.fillRect(0, 0, w, h);
        
        g2.setPaint(Color.BLACK.brighter());
		g2.setStroke(new BasicStroke(10));
		g2.drawRect(125, 100, 420, 120);
		g2.dispose();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void playNote(String note) {
        startSynthesizer();
    
        channel.noteOn(noteIndex + (octave * 12), 90);
        wait(25);
        channel.noteOff(noteIndex + (octave * 12), 90);
	}
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	FinalProject inst = new FinalProject();
                JFrame frame = new JFrame("BEST PIANO EVER");
                frame.setContentPane(inst);
                frame.setBackground(Color.GRAY);
                frame.setBounds(0, 0, 575, 270);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                inst.requestFocusInWindow();
            }
        });
    }
}

/*
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;



public class FinalProject extends JLayeredPane implements KeyListener, ActionListener, MouseListener{
	
	private static final long serialVersionUID = 1L;
	
	private List<Integer> buttonsWhite = new ArrayList<>();
	private List<Integer> buttonsBlack = new ArrayList<>();
	
	
	private List<JButton> keys = new ArrayList<>();
    private boolean[] keyOn = new boolean[21];
    private static final int KEYS_PER_OCTAVE = 12;
    private String[] instruments = {"Grand Piano", "Electronic Piano", "Guitar", "Synth"};
    private ButtonGroup octaveButtons = new ButtonGroup();
    private ButtonModel octave = octaveButtons.getSelection();
	
	public FinalProject() {
		for (int i = 1; i < 37; i++) {
			buttonsWhite.add(i);
		}
		
		setVisible(true);
		setPreferredSize(new Dimension(150, 100));
		
		for (int i = 0; i < 21; i++) {
			JButton button = new JButton();
			button.addActionListener(new ActionListener( ) {
				@Override
				public void actionPerformed(ActionEvent e) {
					playNote("C");
				}
			});
			button.setBackground(Color.WHITE);
			button.setLocation(125 + i * 20, 100);
			button.setSize(20, 120);
			add(button, 0, -1);
			if (buttonsWhite.contains(i))
				keys.add(button);
		}

		
		for (int i = 0; i < 20; i++) {
			if (i == 2 || i == 6 || i == 9 || i == 13 || i == 16) {
				continue;
			}
			JButton button = new JButton();
			button.addActionListener(new ActionListener( ) {
				@Override
				public void actionPerformed(ActionEvent e) {
					playNote("C");
				}
			});
			button.setBackground(Color.BLACK);
			button.setLocation(137 + i * 20, 100);
			button.setSize(15, 70);
			add(button, 1, -1);
		}
		
		JComboBox<String> instrumentDropdown = new JComboBox<>(instruments);
		instrumentDropdown.addActionListener(this);
		instrumentDropdown.setSize(100, 70);
		instrumentDropdown.setLocation(10, 15);
		instrumentDropdown.setOpaque(false);
		add(instrumentDropdown, 1, -1);
		
		for (int i = 0; i < 7; i++) {
			JRadioButton button = makeRadioButton(i);
			octaveButtons.add(button);
			button.addActionListener(new ActionListener( ) {
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			button.setBackground(Color.WHITE);
			button.setLocation(122 + i * 62, 20);
			button.setSize(55, 60);
			button.setOpaque(false);
			add(button, 1, -1);
		}
		
		for (int i = 1; i < 4; i++) {
			JSlider reverb = new JSlider();
			reverb.setSize(100, 20);
			reverb.setLocation(10, 80 + i * 35);
			reverb.setOpaque(false);
			add(reverb, 1, -1);
		}
		
		JLabel delay = new JLabel("Delay");
		delay.setSize(100,20);
		delay.setLocation(45, 90);
		delay.setOpaque(false);
		add(delay, 0, -1);
		
		JLabel decayFactor = new JLabel("Decay Factor");
		decayFactor.setSize(100,20);
		decayFactor.setLocation(25,130);
		decayFactor.setOpaque(false);
		add(decayFactor, 0, -1);
		
		JLabel dryWet = new JLabel("Dry/Wet");
		dryWet.setSize(100,20);
		dryWet.setLocation(40,165);
		dryWet.setOpaque(false);
		add(dryWet, 0, -1);
		
		addKeyListener(this);
	}
	
	public JRadioButton makeRadioButton(int i) {
		if (i != 5) {
			JRadioButton button = new JRadioButton("C" + i, false);
			return button;
		} else {
			JRadioButton button = new JRadioButton("C" + i, true);
			return button;
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		
		
		int w = getWidth();
        int h = getHeight();
        GradientPaint gp = new GradientPaint(0, 0, Color.MAGENTA, w, h, Color.ORANGE);
        g2.setPaint(gp);
        g2.fillRect(0, 0, w, h);
        
        g2.setPaint(Color.BLACK.brighter());
		g2.setStroke(new BasicStroke(10));
		g2.drawRect(125, 100, 420, 120);
		g2.dispose();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void playNote(String note) {
		System.out.println("Playing note: " + note);
	}
	
	
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	FinalProject inst = new FinalProject();
                JFrame frame = new JFrame("BEST PIANO EVER");
                frame.setContentPane(inst);
                frame.setBackground(Color.GRAY);
                frame.setBounds(0, 0, 575, 270);
                frame.setResizable(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                inst.requestFocusInWindow();
            }
        });
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	


}

/*
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    Graphics2D g2 = (Graphics2D) g;
    
    setBackground(new Color(173, 216, 230));
    
    g2.translate(50, 50);
    
    g2.setFont(new Font("TimesRoman", Font.PLAIN, 25));
    
    g2.drawString("Octave: " + octave, 0, -10);

    g2.drawString("Instrument: Grand Piano", 425, -10);
    
    final int WHITE_KEY_WIDTH = 40;
    final int WHITE_KEY_HEIGHT = 150;
    for (int k = 0; k < whiteKeys.length(); k++) {
        int x = k * WHITE_KEY_WIDTH;
        int y = 0;
        
        g2.setPaint(Color.GRAY.darker());
        g2.fillRect(x, y, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
        
        g2.setPaint(Color.WHITE);
        if (k == 0) {
        	g2.fillRect(x + 5, y, WHITE_KEY_WIDTH - 10, WHITE_KEY_HEIGHT - 5);
        } else if (k==13) {
        	g2.fillRect(x + 5, y, WHITE_KEY_WIDTH - 10, WHITE_KEY_HEIGHT - 5);
        } else {
        	g2.fillRect(x + 5, y, WHITE_KEY_WIDTH - 10, WHITE_KEY_HEIGHT - 5);
        }
        
        
        // Draw border
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(x, y, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
        
        // Draw key label
        g2.drawString(" " + whiteKeys.charAt(k), x + 10, y + WHITE_KEY_HEIGHT - 10);
    }
    
    // draw white keys
    final int WHITE_KEY_WIDTH = 40;
    final int WHITE_KEY_HEIGHT = 150;
    for (int k = 0; k < whiteKeys.length(); k++) {
        g2.setColor(keyOn[allKeys.indexOf(whiteKeys.charAt(k))] ? Color.GREEN : Color.WHITE);
        g2.fillRect(k * WHITE_KEY_WIDTH, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(k * WHITE_KEY_WIDTH, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
        g2.drawString(" " + whiteKeys.charAt(k), k * WHITE_KEY_WIDTH + 10, WHITE_KEY_HEIGHT - 10);
    }
    
    

    // draw black keys
    final int BLACK_KEY_WIDTH = WHITE_KEY_WIDTH / 2;
    final int BLACK_KEY_HEIGHT = WHITE_KEY_HEIGHT / 2;
    for (int k = 0; k < blackKeys.length(); k++) {
        if (blackKeys.charAt(k) == ' ') {
            continue;
        }
        int x = (k + 1) * WHITE_KEY_WIDTH - BLACK_KEY_WIDTH / 2;
        g2.setColor(keyOn[allKeys.indexOf(blackKeys.charAt(k))] ? Color.GREEN : Color.BLACK);
        g2.fillRect(x, 1, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
        g2.setColor(Color.GRAY.darker().darker());
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(x, 1, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
        g2.setColor(Color.WHITE);
        g2.drawString(" " + blackKeys.charAt(k), x + 2, BLACK_KEY_HEIGHT - 5);
    }
}
*/

/*
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;



/*
 * Java Virtual Piano
 * 
 * reference: https://docs.oracle.com/javase/tutorial/sound/MIDI-synth.html
 * 
 * @author Leonardo Ono (ono.leo@gmail.com)
*/

/*
public class FinalProject extends JPanel implements KeyListener {
    
    private Synthesizer synthesizer;
    private MidiChannel channel;
    
    private String blackKeys = "WE TYU ";
    private String whiteKeys = "ASDFGHJ";
    private String allKeys = "AWSEDFTGYHUJ";
    
    private int octave = 5; // starts at middle C
    private boolean[] keyOn = new boolean[allKeys.length()];
    private static final int KEYS_PER_OCTAVE = 12;
    
    public FinalProject() {
        addKeyListener(this);
        startSynthesizer();
    }

    private void startSynthesizer()  {
        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channel = synthesizer.getChannels()[0];
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        
        g.translate(50, 50);
        
        g.drawString("Octave: " + octave, 0, -10);
        
        // draw white keys
        final int WHITE_KEY_WIDTH = 40;
        final int WHITE_KEY_HEIGHT = 100;
        for (int k = 0; k < whiteKeys.length(); k++) {
            g.setColor(keyOn[allKeys.indexOf(whiteKeys.charAt(k))] ? Color.GREEN : Color.WHITE);
            g.fillRect(k * WHITE_KEY_WIDTH, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
            g.setColor(Color.BLACK);
            g.drawRect(k * WHITE_KEY_WIDTH, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
            g.drawString(" " + whiteKeys.charAt(k), k * WHITE_KEY_WIDTH + 10, WHITE_KEY_HEIGHT - 10);
        }

        // draw black keys
        final int BLACK_KEY_WIDTH = WHITE_KEY_WIDTH / 2;
        final int BLACK_KEY_HEIGHT = WHITE_KEY_HEIGHT / 2;
        for (int k = 0; k < blackKeys.length(); k++) {
            if (blackKeys.charAt(k) == ' ') {
                continue;
            }
            int x = (k + 1) * WHITE_KEY_WIDTH - BLACK_KEY_WIDTH / 2;
            g.setColor(keyOn[allKeys.indexOf(blackKeys.charAt(k))] ? Color.GREEN : Color.BLACK);
            g.fillRect(x, 1, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
            g.setColor(Color.WHITE);
            g.drawRect(x, 1, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
            g.drawString(" " + blackKeys.charAt(k), x + 2, BLACK_KEY_HEIGHT - 5);
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        repaint();
        
        // select octave using 0~8 keys
        if ("012345678".contains("" + e.getKeyChar())) {
            octave = e.getKeyCode() - 48;
        }
        
        // select octave using '+' or '-' keys
        if (e.getKeyChar() == '+') {
            octave = octave < 8 ? octave + 1 : 8;
        }
        else if (e.getKeyChar() == '-') {
            octave = octave > 0 ? octave - 1 : 0;
        }
        
        // play a note
        int noteIndex = allKeys.indexOf((char) e.getKeyCode()); 
        if (noteIndex < 0 || keyOn[noteIndex]) {
            return;
        }
        keyOn[noteIndex] = true;
        channel.noteOn(octave * KEYS_PER_OCTAVE + noteIndex, 90);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        repaint();
        int noteIndex = allKeys.indexOf((char) e.getKeyCode()); 
        if (noteIndex < 0) {
            return;
        }
        keyOn[noteIndex] = false;
        channel.noteOff(octave * KEYS_PER_OCTAVE + noteIndex);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FinalProject view = new FinalProject();
                JFrame frame = new JFrame();
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.getContentPane().add(view);
                frame.setVisible(true);
                view.requestFocus();
            }
        });
    }
    
}
*/
