package com.oasisdex.models;

	public enum BrowserTypes
	{
		CHROME("chrome"),
		FIREFOX("firefox"),
		IE("ie"),
		SAFARI("safari");

		String label;

		private BrowserTypes(String label)
		{
			this.label = label;
		}

		public String getLabel()
		{
			return this.label;
		}

		public String toString()
		{
			return this.label;
		}
	}

