import React from 'react';
import styled from 'styled-components';
import palette from '../../../lib/styles/palette';

const StyledButton = styled.button`
  @font-face {
    font-family: 'LINESeedKR-Bd';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2')
      format('woff2');
    font-weight: 700;
    font-style: normal;
  }

  border: rgba(7, 7, 7, 0.16);
  border-radius: 5px;
  font-size: 25px;
  font-weight: bold;
  padding: 0.5rem 1rem;
  margin-left: 20px;
  color: black;
  outline: #e8e8e8;
  cursor: pointer;
  background: white;
  opacity: 80%;
  font-family: 'LINESeedKR-Bd';

  &:hover {
    background: ${palette.dreamhi[0]};
  }
`;

function Button(props) {
  const { title, onClick } = props;

  return <StyledButton onClick={onClick}>{title || 'button'}</StyledButton>;
}

export default Button;
