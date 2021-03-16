/*
 * Copyright 2002-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.transaction.annotation;

import org.springframework.transaction.TransactionDefinition;

/**
 * Enumeration that represents transaction propagation behaviors for use
 * with the {@link Transactional} annotation, corresponding to the
 * {@link TransactionDefinition} interface.
 *
 * @author Colin Sampaleanu
 * @author Juergen Hoeller
 * @since 1.2
 */
public enum Propagation {

	/**
	 1.PROPAGATION_REQUIRED与PROPAGATION_MANDATORY
	 	PROPAGATION_REQUIRED：必须要在事务中运行，没有事务就开启新事务
	 	PROPAGATION_MANDATORY：必须要在事务中运行，没有事务就抛异常
	 2.PROPAGATION_NOT_SUPPORTED与PROPAGATION_NEVER
	 	PROPAGATION_NOT_SUPPORTED：不能在事务中运行，有事务就挂起事务
	 	PROPAGATION_NEVER：不能在事务中运行，有事务就抛异常
	 3.PROPAGATION_REQUIRES_NEW与PROPAGATION_NESTED
	 	PROPAGATION_REQUIRES_NEW：新事务执行完成后，旧事务报错，只回滚旧事务，新事务不回滚；新事务执行报错，新旧事务一起回滚
	 	PROPAGATION_NESTED：子事务执行完成后，父事务报错，回滚到保存点；子事务执行报错，也是回滚到保存点
	 */


	/**
	 * Support a current transaction, create a new one if none exists.
	 * Analogous to EJB transaction attribute of the same name.
	 * <p>This is the default setting of a transaction annotation.
	 */
	//【默认值：必需】当前方法必须在事务中运行，如果当前线程中没有事务，则开启一个新的事务；
	// 如果当前线程中已经存在事务，则方法将会在该事务中运行
	REQUIRED(TransactionDefinition.PROPAGATION_REQUIRED),

	/**
	 * Support a current transaction, execute non-transactionally if none exists.
	 * Analogous to EJB transaction attribute of the same name.
	 * <p>Note: For transaction managers with transaction synchronization,
	 * {@code SUPPORTS} is slightly different from no transaction at all,
	 * as it defines a transaction scope that synchronization will apply for.
	 * As a consequence, the same resources (JDBC Connection, Hibernate Session, etc)
	 * will be shared for the entire specified scope. Note that this depends on
	 * the actual synchronization configuration of the transaction manager.
	 * @see org.springframework.transaction.support.AbstractPlatformTransactionManager#setTransactionSynchronization
	 */

	//【支持】当前方法单独运行时不需要事务，但如果当前线程中存在事务时，方法会在事务中运行
	SUPPORTS(TransactionDefinition.PROPAGATION_SUPPORTS),

	/**
	 * Support a current transaction, throw an exception if none exists.
	 * Analogous to EJB transaction attribute of the same name.
	 */
	//【强制】当前方法必须在事务中运行，如果当前线程中不存在事务，则抛出异常
	MANDATORY(TransactionDefinition.PROPAGATION_MANDATORY),

	/**
	 * Create a new transaction, and suspend the current transaction if one exists.
	 * Analogous to the EJB transaction attribute of the same name.
	 * <p><b>NOTE:</b> Actual transaction suspension will not work out-of-the-box
	 * on all transaction managers. This in particular applies to
	 * {@link org.springframework.transaction.jta.JtaTransactionManager},
	 * which requires the {@code javax.transaction.TransactionManager} to be
	 * made available to it (which is server-specific in standard Java EE).
	 * @see org.springframework.transaction.jta.JtaTransactionManager#setTransactionManager
	 */
	//【新事务】当前方法必须在独立的事务中运行，如果当前线程中已经存在事务，
	// 则将该事务挂起，重新开启一个事务，直到方法运行结束再恢复之前的事务
	REQUIRES_NEW(TransactionDefinition.PROPAGATION_REQUIRES_NEW),

	/**
	 * Execute non-transactionally, suspend the current transaction if one exists.
	 * Analogous to EJB transaction attribute of the same name.
	 * <p><b>NOTE:</b> Actual transaction suspension will not work out-of-the-box
	 * on all transaction managers. This in particular applies to
	 * {@link org.springframework.transaction.jta.JtaTransactionManager},
	 * which requires the {@code javax.transaction.TransactionManager} to be
	 * made available to it (which is server-specific in standard Java EE).
	 * @see org.springframework.transaction.jta.JtaTransactionManager#setTransactionManager
	 */
	//【不支持】当前方法不会在事务中运行，如果当前线程中存在事务，则将事务挂起，直到方法运行结束
	NOT_SUPPORTED(TransactionDefinition.PROPAGATION_NOT_SUPPORTED),

	/**
	 * Execute non-transactionally, throw an exception if a transaction exists.
	 * Analogous to EJB transaction attribute of the same name.
	 */
	//【不允许】当前方法不允许在事务中运行，如果当前线程中存在事务，则抛出异常
	NEVER(TransactionDefinition.PROPAGATION_NEVER),

	/**
	 * Execute within a nested transaction if a current transaction exists,
	 * behave like {@code REQUIRED} otherwise. There is no analogous feature in EJB.
	 * <p>Note: Actual creation of a nested transaction will only work on specific
	 * transaction managers. Out of the box, this only applies to the JDBC
	 * DataSourceTransactionManager. Some JTA providers might support nested
	 * transactions as well.
	 * @see org.springframework.jdbc.datasource.DataSourceTransactionManager
	 */
	//【嵌套】当前方法必须在事务中运行，如果当前线程中存在事务，则将该事务标注保存点，形成嵌套事务。
	// 嵌套事务中的子事务出现异常不会影响到父事务保存点之前的操作。
	NESTED(TransactionDefinition.PROPAGATION_NESTED);


	private final int value;


	Propagation(int value) {
		this.value = value;
	}

	public int value() {
		return this.value;
	}

}
