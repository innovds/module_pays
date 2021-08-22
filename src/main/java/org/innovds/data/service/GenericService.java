package org.innovds.data.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("prototype")
public class GenericService<T, ID> extends AbstractGenericService<T, ID> {

}