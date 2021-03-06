package org.matheusdev.util.matrix.matrix3;

/**
 * @author matheusdev
 *
 */
public class MatrixN3s extends MappedMatrix3 {

	public short[] values;

	public MatrixN3s(int w, int h, int d) {
		super(w, h, d);
		values = new short[getSize()];
	}

	public short get(int x, int y, int z) {
		return values[getPosition(x, y, z)];
	}

	public void set(short val, int x, int y, int z) {
		values[getPosition(x, y, z)] = val;
	}

}
