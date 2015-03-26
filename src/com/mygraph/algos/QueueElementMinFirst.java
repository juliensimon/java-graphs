package com.mygraph.algos;

import java.util.Comparator;

import com.mygraph.core.Vertex;

class QueueElementMinFirst<T extends Vertex> implements
		Comparator<QueueElement<T>> {
	@Override
	public int compare(QueueElement<T> e1, QueueElement<T> e2) {
		int i1 = e1.getDistance();
		int i2 = e2.getDistance();
		if (i1 == i2) {
			return 0;
		}
		if (i1 > i2) {
			return 1;
		} else {
			return -1;
		}
	}
}