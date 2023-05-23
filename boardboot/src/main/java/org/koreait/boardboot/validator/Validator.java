package org.koreait.boardboot.validator;

public interface Validator<T> extends RequiredValidator{
    void check(T t);
}
