/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author USER
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CedulaFilter extends DocumentFilter {

    @Override
    public void replace(FilterBypass fb, int offset, int length,
            String texto, AttributeSet attrs) throws BadLocationException {

        String actual = fb.getDocument().getText(0, fb.getDocument().getLength());

        String nuevoTexto = actual.substring(0, offset)
                + (texto == null ? "" : texto)
                + actual.substring(offset + length);

        if (nuevoTexto.matches("\\d{0,10}")) {
            super.replace(fb, offset, length, texto, attrs);
        }
    }
}