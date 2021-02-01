/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignments.Assignment_1.Assignment_2;

/**
 *
 * @author bharg
 */
/*
 * From Data Structures and Algorithms in Java, Sixth Edition, Goodrich et al.
 */

/**
 * An interface for a position which is an abstraction for the
 * location at which a single element is stored in a positional
 * container.
 */
public interface Position<E> {
  /**
   * Returns the element stored at this position.
   *
   * @return the stored element
   * @throws IllegalStateException if position no longer valid
   */
  E getElement() throws IllegalStateException;
}
