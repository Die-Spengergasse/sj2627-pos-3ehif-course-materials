package at.spengergasse.pos3.modernjava.expressionevaluator;

public sealed interface Expr permits Num, Add, Mul {
}
