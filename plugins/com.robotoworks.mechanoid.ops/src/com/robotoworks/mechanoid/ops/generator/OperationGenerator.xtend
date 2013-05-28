package com.robotoworks.mechanoid.ops.generator

import com.robotoworks.mechanoid.ops.opServiceModel.Model
import com.robotoworks.mechanoid.ops.opServiceModel.Operation

import static extension com.robotoworks.mechanoid.ops.generator.Extensions.*
import static extension com.robotoworks.mechanoid.text.Strings.*
import com.robotoworks.mechanoid.ops.opServiceModel.UniqueDeclaration

class OperationGenerator {
		def CharSequence generate(Model model, Operation op) '''
			�var svc = model.service�
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package �model.packageName�;

			import com.robotoworks.mechanoid.Mechanoid;
			import com.robotoworks.mechanoid.ops.Operation;
			import com.robotoworks.mechanoid.ops.OperationResult;
			import com.robotoworks.mechanoid.ops.OperationServiceBridge;
			import com.robotoworks.mechanoid.ops.OperationConfiguration;
			import android.content.Intent;
			import android.os.Bundle;
			
			public abstract class Abstract�op.name.pascalize�Operation extends Operation {
				public static final String ACTION_�op.name.underscore.toUpperCase� = "�model.packageName�.�svc.name.formatServiceName�.actions.�op.name.underscore.toUpperCase�";
			
				�FOR arg : op.args�
				public static final String EXTRA_�arg.name.underscore.toUpperCase� = "�model.packageName�.�svc.name.formatServiceName�.extras.�arg.name.underscore.toUpperCase�";
				�ENDFOR�
			
				static class Args {
					�FOR arg : op.args�
					public �arg.type.toTypeLiteral� �arg.name.camelize�;
					�ENDFOR�
				}
				
				static class Configuration extends OperationConfiguration {
					@Override 
					public Operation createOperation() {
						return new �op.name.pascalize�Operation();
					}
					
					@Override
					public Intent findMatchOnConstraint(OperationServiceBridge bridge, Intent intent) {
						�IF op.uniqueClause == null�
						Intent existingRequest = bridge.findPendingRequestByActionWithExtras(Abstract�op.name.pascalize�Operation.ACTION_�op.name.underscore.toUpperCase�, intent.getExtras());
						
						return existingRequest;
						
						�ELSEIF op.uniqueClause instanceof UniqueDeclaration�
						�var uniqueDecl = op.uniqueClause as UniqueDeclaration�
						android.os.Bundle matcher = new android.os.Bundle();
						android.os.Bundle intentExtras = intent.getExtras();
						�FOR uarg : uniqueDecl.args�
						matcher.�uarg.type.toBundlePutMethodName�(
							�op.name.pascalize�Operation.EXTRA_�uarg.name.underscore.toUpperCase�, 
							intentExtras.�uarg.type.toBundleGetMethodName�(�op.name.pascalize�Operation.EXTRA_�uarg.name.underscore.toUpperCase�));
						�ENDFOR�
						
						Intent existingRequest = bridge.findPendingRequestByActionWithExtras(Abstract�op.name.pascalize�Operation.ACTION_�op.name.underscore.toUpperCase�, matcher);

						return existingRequest;
						�ELSE�
						return null;
						�ENDIF�
					}
				}
				
				public static final Intent newIntent(�FOR arg: op.args SEPARATOR ', '��arg.type.toTypeLiteral� �arg.name.camelize��ENDFOR�) {
					Intent intent = new Intent(ACTION_�op.name.underscore.toUpperCase�);
					intent.setClass(Mechanoid.getApplicationContext(), �svc.name.formatServiceName�.class);
					
					Bundle extras = new Bundle();
					�FOR arg : op.args�
					extras.�arg.type.toBundlePutMethodName�(EXTRA_�arg.name.underscore.toUpperCase�, �arg.name.camelize�);
					�ENDFOR�
					
					intent.putExtras(extras);
					
					return intent;
					
				}
			
				@Override
				public OperationResult execute() {
					Args args = new Args();
					Bundle extras = getIntent().getExtras();
					�FOR arg : op.args�
					args.�arg.name.camelize� = extras.�arg.type.toBundleGetMethodName�(EXTRA_�arg.name.underscore.toUpperCase�);
					�ENDFOR�
					
					return onExecute(args);
				}
						
				protected abstract OperationResult onExecute(Args args);
			}
			'''
			
		def CharSequence generateStub(Model model, Operation op) '''
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package �model.packageName�;
			
			import �model.packageName�.Abstract�op.name.pascalize�Operation;
			import com.robotoworks.mechanoid.ops.OperationResult;
			
			public class �op.name.pascalize�Operation extends Abstract�op.name.pascalize�Operation {
				@Override
				protected OperationResult onExecute(Args args) {
					// TODO Auto-generated method stub
					return null;
				}
			}
		'''
}