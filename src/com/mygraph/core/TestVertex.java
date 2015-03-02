package com.mygraph.core;


import static org.junit.Assert.*;
import org.junit.Test;
import com.mygraph.core.Vertex;

public class TestVertex {

	@Test
	public void testGetId() {
		
		Vertex v = new Vertex(5);
		assertEquals(v.getId(), 5);
	}

}
