Spring AI + Gemini Flash + MCP Server
This project demonstrates a multi-modal AI application using Spring AI (1.1.2+) and Google Gemini 1.5 Flash. It features a local Model Context Protocol (MCP) integration. This allows the LLM to execute local Node.js tools, like weather or product lookups, while processing text and images.
Features
Gemini 1.5 Flash: High-speed, multi-modal LLM for text and image analysis.
MCP Integration: Connects to a local Node.js server via STDIO. It provides real-time tool capabilities.
Multi-modal Support: Endpoint to analyze images using Spring AI's Media API.
Automatic Tool Discovery: Spring AI automatically maps Node.js MCP tools to Gemini function calls.
Prerequisites
Java 17 or higher.
Node.js (v18+) installed locally.
Google AI API Key. Get one from the Google AI Studio.

1. Set up the Node.js MCP Server
   Make sure the MCP server is compiled to JavaScript.
   Go to your MCP server directory: cd ../mcp-server
   Install dependencies: npm install
   Build the project: npm run build. Ensure dist/index.js is generated.
2. Configuration (application.properties)
   Replace the placeholder paths and API keys with your actual values.
     spring.ai.openai.api-key=${GEMINI_API_KEY}
     spring.ai.mcp.client.stdio.connections.local-node-server.command=node
     spring.ai.mcp.client.stdio.connections.local-node-server.args=C:\\Users\\dell\\IdeaProjects\\mcp-server\\src\\index.ts
3. Project Structure
   Chat Client Configuration
   The project uses a ChatClient bean configured with the MCP ToolCallbackProvider
   @Bean
   public ChatClient chatClient(ChatClient.Builder builder, ToolCallbackProvider mcpToolProvider) {
   return builder
   .defaultToolCallbacks(mcpToolProvider) // Automatically enables MCP tools
   .build();
   }

Controller Endpoints
Method	Endpoint	                Description
GET	    /chat-with-gemini?msg=...	Standard chat with tool-use ability.
POST	/from-files	                Upload an image and a prompt.

WITH MCP SERVER RUNNING:
GET http://localhost:8989/chat-gemini-mcp?msg=Can you list all the added products?

Note: we can also use direct Clause desktop app to use MCP server functionalities