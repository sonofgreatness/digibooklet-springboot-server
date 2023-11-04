function Prompt
 {                                                                      
>>     "PS $($executionContext.SessionState.Path.CurrentLocation.Path.Split('\')[-1])$('>' * ($nestedPromptLevel
 +1)) "
>> }

