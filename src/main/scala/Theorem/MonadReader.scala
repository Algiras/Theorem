package Theorem

trait MonadReader[F[_], S] extends Monad[F]{
  def ask: F[S]
  def local[A](fn: S => S)(fa: F[A]): F[A]
  def scope[A](env: S)(fa: F[A]): F[A] = local(_ => env)(fa)
  def asks[A](fn: S => A): F[A] = map[S, A](ask)(fn)
}

object MonadReader {
  @inline def apply[F[_], S](implicit F: MonadReader[F, S]): MonadReader[F, S] = F
}
