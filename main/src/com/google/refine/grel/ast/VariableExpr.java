/*

Copyright 2010, Google Inc.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

    * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above
copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.
    * Neither the name of Google Inc. nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,           
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY           
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

package com.google.refine.grel.ast;

import java.util.Collections;
import java.util.Properties;
import java.util.Set;

import com.google.refine.expr.Evaluable;

/**
 * An abstract syntax tree node encapsulating the retrieval of a variable's content.
 */
public class VariableExpr implements Evaluable {
    final protected String _name;
    
    public VariableExpr(String name) {
        _name = name;
    }
    
    @Override
    public Object evaluate(Properties bindings) {
        return bindings.get(_name);
    }

    @Override
    public String toString() {
        return _name;
    }
    
    public String getName() {
        return _name;
    }
    
    @Override
    public boolean equals(Object other) {
    	return (other instanceof Evaluable) && toString().equals(other.toString());
    }

	@Override
	public Set<String> getColumnDependencies(String baseColumn) {
		if("value".equals(_name) || "cell".equals(_name) || "recon".equals(_name)) {
			return Collections.singleton(baseColumn);
		} else if ("cells".equals(_name) || "row".equals(_name) || "record".equals(_name)) {
			return null;
		}
		return Collections.emptySet();
	}
}
