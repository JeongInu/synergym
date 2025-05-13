import { Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Join from "./pages/Join";
import Post from "./pages/Post";
import PostWrite from "./pages/PostWrite";
import PostDetail from "./pages/PostDetail";


const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/join" element={<Join />} />
      <Route path="/posts" element={<Post />} />
      <Route path="/posts/write" element={<PostWrite />} />
      <Route path="/posts/:postId" element={<PostDetail />} />
    </Routes>
  );
};

export default App;
