/**
 * CELINE SCORM
 *
 * Copyright 2014 Adilson Vahldick.
 * https://celine-scorm.googlecode.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.univali.celine.scorm2004_4th.sn.sb.seqreqprocess;

import br.univali.celine.scorm.sn.model.ActivityTree;
import br.univali.celine.scorm.sn.sb.seqreqprocess.ResultSequencingRequestCommand;
import br.univali.celine.scorm.sn.sb.seqreqprocess.SequencingRequestCommand;

public class JumpSequencingRequestProcess implements SequencingRequestCommand {

	@Override
	public ResultSequencingRequestCommand run(ActivityTree activityTree) {
		
		
		if (activityTree.getCurrentActivity() == null)
			// Make sure the sequencing session has not already begun.
			return new ResultSequencingRequestCommand(56); // SB.2.13-1
		else {
			ResultSequencingRequestCommand rsr;
			rsr = new ResultSequencingRequestCommand();
			rsr.setDeliveryRequest(activityTree.getTargetActivity());
			
			return rsr;
		}

	}

}