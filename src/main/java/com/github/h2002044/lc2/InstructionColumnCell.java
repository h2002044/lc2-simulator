package com.github.h2002044.lc2;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.*;


class InstructionColumnCell extends JTextField
{

    public InstructionColumnCell()
    {
        super();
    }

    public InstructionColumnCell(int iColumnCount)
    {
        super(iColumnCount);
          
    }


    protected void processKeyEvent(KeyEvent e)
    {

        if (e.getModifiers() == 0)
        {
            int iCaretPosition = this.getCaretPosition();
            int keyChar = e.getKeyChar();
            int keyCode = e.getKeyCode();

            if (this.getSelectionStart() == 0 || this.getSelectionStart() == 1 || this.getSelectionEnd() == 1 || this.getSelectionEnd() == 0)
            {
                if (keyCode == 39 || keyCode == 37 || keyCode == 35 || keyCode == 36)
                {

                }
                else
                {
                    return;
                }
            }

            if ((iCaretPosition == 0 || iCaretPosition == 1) && (keyCode == 37 || keyCode == 39 || keyCode == 35 || keyCode == 36))
            {
                super.processKeyEvent(e);

            }

            if (iCaretPosition > 1 && iCaretPosition <= 6 && this.getText().length() <= 6)
            {
                if ((keyChar >= '0' && keyChar <= '9') || keyChar == 'A' || keyChar == 'B' || keyChar == 'C' || keyChar == 'D' || keyChar == 'E' || keyChar == 'F' || keyChar == 'a' || keyChar == 'b' || keyChar == 'c' || keyChar == 'd' || keyChar == 'e' || keyChar == 'f' || keyChar == e.VK_KP_LEFT || keyChar == e.VK_KP_RIGHT || keyChar == e.VK_SHIFT)
                {
                    if (this.getText().length() < 6)
                    {
                        if (!((keyChar == e.VK_BACK_SPACE || keyCode == 37) && iCaretPosition == 2))
                        {
                            super.processKeyEvent(e);
                        }
                    }
                }
                else if (keyCode == 39 || keyCode == 37 || keyCode == 155 || keyChar == e.VK_DELETE || keyChar == e.VK_BACK_SPACE || keyCode == 35 || keyCode == 36)
                {
                    if (!((keyChar == e.VK_BACK_SPACE || keyChar == 37) && iCaretPosition == 2))
                    {
                        super.processKeyEvent(e);
                    }
                }
            }
        }
    }
}
