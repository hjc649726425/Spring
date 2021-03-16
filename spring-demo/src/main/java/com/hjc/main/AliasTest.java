package com.hjc.main;

import org.springframework.core.SimpleAliasRegistry;

import java.util.Arrays;

public class AliasTest {

	public static void main(String[] args) throws InterruptedException {
		SimpleAliasRegistry aliasRegistry = new SimpleAliasRegistry();
		aliasRegistry.registerAlias("B", "A");
		aliasRegistry.registerAlias("A", "C");
		aliasRegistry.registerAlias("C", "D");
		System.out.println("B的别名:" + Arrays.toString(aliasRegistry.getAliases("B")));
		System.out.println("A的别名:" + Arrays.toString(aliasRegistry.getAliases("A")));
		System.out.println("C的别名:" + Arrays.toString(aliasRegistry.getAliases("C")));
	}
}
