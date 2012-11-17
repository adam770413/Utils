/*
 * Copyright (c) 2012 matheusdev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to
 * deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.matheusdev.util.vecmath;

import java.nio.DoubleBuffer;


/**
 * @author matheusdev
 *
 */
public class Vec2d implements DoubleBuffable<Vec2d> {

	public double x;
	public double y;

	public Vec2d() {
		this(0, 0);
	}

	public Vec2d(double x, double y) {
		set(x, y);
	}

	public Vec2d set(double x, double y) {
		this.x = x;
		this.y = y;
		return this;
	}

	public Vec2d set(Vec2d toCopy) {
		return set(toCopy.x, toCopy.y);
	}

	public Vec2d scale(double factor) {
		return scale(factor, factor);
	}

	public Vec2d scale(double xs, double ys) {
		this.x *= xs;
		this.y *= ys;
		return this;
	}

	public double len() {
		return Math.sqrt(sqLen());
	}

	public double sqLen() {
		return x * x + y * y;
	}

	public double directionRad() {
		return Math.atan2(y, x);
	}

	public double directionDeg() {
		return Math.toDegrees(directionRad());
	}

	public Vec2d normalize() {
		return scale(len());
	}

	public Vec2d normalize(Vec2d dest) {
		dest = getDest(dest);

		dest.set(this);
		dest.normalize();

		return dest;
	}

	public Vec2d translate(double xt, double yt) {
		this.x += xt;
		this.y += yt;
		return this;
	}

	public Vec2d negate(Vec2d dest) {
		dest = getDest(dest);

		dest.x = -x;
		dest.y = -y;

		return this;
	}

	/* (non-Javadoc)
	 * @see org.matheusdev.util.vecmath.doubleBuffable#load(java.nio.doubleBuffer)
	 */
	@Override
	public Vec2d load(DoubleBuffer buf) {
		this.x = buf.get();
		this.y = buf.get();
		return this;
	}

	/* (non-Javadoc)
	 * @see org.matheusdev.util.vecmath.doubleBuffable#store(java.nio.doubleBuffer)
	 */
	@Override
	public Vec2d store(DoubleBuffer buf) {
		buf.put(x).put(y);
		return this;
	}

	public static Vec2d add(Vec2d left, Vec2d right, Vec2d dest) {
		dest = getDest(dest); // Create new one if needed.

		dest.x = left.x + right.x;
		dest.y = left.y + right.y;

		return dest;
	}

	public static Vec2d sub(Vec2d left, Vec2d right, Vec2d dest) {
		dest = getDest(dest); // Create new one if needed.

		dest.x = left.x - right.x;
		dest.y = left.y - right.y;

		return dest;
	}

	public static Vec2d mul(Vec2d left, Vec2d right, Vec2d dest) {
		dest = getDest(dest); // Create new one if needed.

		dest.x = left.x * right.x;
		dest.y = left.y * right.y;

		return dest;
	}

	public static Vec2d div(Vec2d left, Vec2d right, Vec2d dest) {
		dest = getDest(dest); // Create new one if needed.

		dest.x = left.x / right.x;
		dest.y = left.y / right.y;

		return dest;
	}

	public static double dot(Vec2d left, Vec2d right) {
		return left.x * right.x + left.y * right.y;
	}

	public static double angleRad(Vec2d v1, Vec2d v2) {
		double d = dot(v1, v2) / (v1.len() * v2.len());

		if (d < -1f) {
			d = -1f;
		} else if (d > 1.0f) {
			d = 1.0f;
		}

		return Math.acos(d);
	}

	public static double angleDeg(Vec2d v1, Vec2d v2) {
		return Math.toDegrees(angleRad(v1, v2));
	}

	protected static Vec2d getDest(Vec2d dest) {
		return dest == null ? new Vec2d() : dest;
	}

}