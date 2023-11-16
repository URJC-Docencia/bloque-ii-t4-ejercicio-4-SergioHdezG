package material;

/**
 * An interface for a position, which is a holder object storing a
 * single element.
 * @author A. Duarte, J. Vélez
 * @param <E>
 */
public interface Position<E> {
  /** Return the element stored at this position. */
  E getElement();
}
